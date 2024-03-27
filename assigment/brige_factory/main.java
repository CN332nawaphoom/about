import java.awt.Graphics2D;
import java.awt.Dimension;
// import java.awt.Color;
// import java.awt.BasicStroke;
import java.awt.Graphics;

// import java.awt.geom.Line2D;
import javax.swing.*;


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

    public static void main(String[] args) {
        JFrame frame = createFrame();

        
        
       
        

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
