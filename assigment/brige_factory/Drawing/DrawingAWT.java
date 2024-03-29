package Drawing;

// import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
// shape class
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Polygon;

import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Shape.MyShape;

public class DrawingAWT extends JPanel implements Drawing {
    private List<MyShape> shapes = new ArrayList<>();
    private JFrame frame;

    public DrawingAWT() {
        this.setPreferredSize(new Dimension(300, 300));
        frame = createFrame();
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (MyShape item : shapes) {
            Shape awtShape = createShape(item);

            g2d.setColor(item.get_line_color());
            g2d.draw(awtShape); // Outline of the shape

            g2d.setColor(item.get_area_color());
            g2d.fill(awtShape); // Fill the shape with color
        }
    }


    private static Shape createShape(MyShape myshape){
        if (myshape != null) {
            String shapeName = myshape.getClass().getSimpleName();
            if (shapeName.equalsIgnoreCase("MyRectangle")) {
                int[] position_data = myshape.get_position_data();
                int x = position_data[0];
                int y = position_data[1];
                int width = position_data[2];
                int height = position_data[3];

                return new Rectangle(x, y, width, height);
            }

            else if (shapeName.equalsIgnoreCase("MyCircle")){
                int[] position_data = myshape.get_position_data();
                int x = position_data[0];
                int y = position_data[1];
                int width = position_data[2]*2;
                int height = position_data[2]*2;
                return new Ellipse2D.Double(x,y,width,height);
            }

            else if (shapeName.equalsIgnoreCase("MyTriangle")){
                int[] position_data = myshape.get_position_data();
                int[] x  = new int[3];  
                int[] y  = new int[3];  
                for (int i=0;i<3;i++){
                    x[i] = position_data[i];
                    y[i] = position_data[i+3];
                }
                return new Polygon(x,y,x.length);
            }

        } else {
            System.err.println("Cannot create null shape.");
        }
    
        return null; 
    }
    
    public void paintShape(MyShape shape) {
        if (shape != null) {
            shapes.add(shape);
            repaint();
        } else {
            System.err.println("Cannot add null shape.");
        }
    }

    @Override
    public void saveImage(String format_name, String file_path) {
        BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        frame.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, format_name, new File(file_path));
            System.out.println("Panel saved as " + format_name + ": " + file_path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
