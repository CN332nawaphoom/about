package Drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Shape.MyRectangle;
import Shape.MyShape;

public class DrawingAWT extends JPanel implements Drawing {
    private List<ShapeInfo> shapes = new ArrayList<>();
    private JFrame frame;
    
    private static class ShapeInfo {
        MyShape shape;
        Color lineColor;
        Color areaColor;
        int[] position_data;

        public ShapeInfo(MyShape shape, Color lineColor, Color areaColor, int[] position_data) {
            this.shape = shape;
            this.lineColor = lineColor;
            this.areaColor = areaColor;
            this.position_data = position_data;
        }
    }

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

        for (ShapeInfo item : shapes) {
            Shape awtShape = createShape(item);

            g2d.setColor(item.lineColor);
            g2d.draw(awtShape); // Outline of the shape

            g2d.setColor(item.areaColor);
            g2d.fill(awtShape); // Fill the shape with color
        }
    }

    private static Shape createShape(ShapeInfo s){
        MyShape myShape = s.shape;
        int x = s.position_data[0];
        int y = s.position_data[1];
        int width = s.position_data[2];
        int height = s.position_data[3];
    
        if (myShape != null) {
            String shapeName = myShape.getClass().getSimpleName();
            if (shapeName.equalsIgnoreCase("MyRectangle")) {
                return new Rectangle(x, y, width, height);
            }
            // TODO more MyShape
        } else {
            System.err.println("Cannot create null shape.");
        }
    
        return null; 
    }
    
    public void paintShape(MyShape shape, Color lineColor, Color areaColor, int[] position_data) {
        if (shape != null) {
            shapes.add(new ShapeInfo(shape, lineColor, areaColor, position_data));
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
