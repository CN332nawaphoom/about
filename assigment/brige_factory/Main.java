import java.awt.Graphics2D;
import java.awt.Dimension;
// import java.awt.Color;
// import java.awt.BasicStroke;
import java.awt.Graphics;

import javax.imageio.ImageIO;
// import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main extends JPanel{
    Main(){
        this.setPreferredSize(new Dimension(300, 300));
    }

    public static JFrame createFrame(){
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Main());
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(0, 0, 300, 300);
    }

    public static void saveImage(JFrame frame,String formatname, String filePath){
        BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        frame.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, "PNG", new File(filePath));
            System.out.println("Panel saved as PNG: " + filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = createFrame();
        saveImage(frame,"png", "C:\\Users\\paipa\\Pictures\\test2.png");


        
        
       
        

        // try {
        //     String directoryPath = System.getProperty("user.dir");
        //     String item = "\\configs.json";
        //     Factory a = new Factory(directoryPath + item, "json");
        //     System.out.println(a.readConfig("name"));
        // } catch (FileNotFoundException e) {
        //     System.out.println("not found");
        // }
            
    }

    
}
