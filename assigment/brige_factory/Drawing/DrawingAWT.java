package Drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Shape.MyShape;

public class DrawingAWT extends JPanel implements Drawing{
    private Shape custom_shape;
    private Color custom_line_color;
    private Color custom_area_color;

    private JFrame frame;

    public Shape get_customShape() {
        return custom_shape;
    }

    public void set_customShape(Shape customShape) {
        this.custom_shape = customShape;
    }

    public Color get_custom_line_color() {
        return custom_line_color;
    }

    public void set_custom_line_color(Color custom_line_color) {
        this.custom_line_color = custom_line_color;
    }

    public Color get_custom_area_color() {
        return custom_area_color;
    }

    public void set_custom_area_color(Color custom_area_color) {
        this.custom_area_color = custom_area_color;
    }

    public DrawingAWT(){
        this.setPreferredSize(new Dimension(300, 300));
        frame = createFrame();
    }

    private JFrame createFrame(){
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
        Graphics2D g2d = (Graphics2D) g;

        if(custom_shape != null){
            // Draw the custom shape
            g2d.setColor(custom_line_color);
            g2d.draw(custom_shape); // Outline of the shape

            // Fill the custom shape
            g2d.setColor(custom_area_color);
            g2d.fill(custom_shape); // Fill the shape with color
        }
    }

    public void paint(MyShape s, String line_color, String area_color) {
        String className = s.getClass().getSimpleName();
        int x = s.get_position_data()[0];
        int y = s.get_position_data()[1];
        int width = s.get_position_data()[2];
        int height = s.get_position_data()[3];

        custom_line_color = Color.decode(line_color);
        custom_area_color = Color.decode(area_color);

        if (className.equalsIgnoreCase("MyRectangle")){
            Shape shape = new Rectangle(x,y,width,height);
            custom_shape = shape;
        }

        paint(getGraphics());
    }

    @Override
    public void saveImage(String format_name, String file_path) {
        BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        frame.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, "PNG", new File(file_path));
            System.out.println("Panel saved as PNG: " + file_path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
