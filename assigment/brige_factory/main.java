import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import java.io.FileNotFoundException;


public class main extends JPanel {
    ;
    public static void main(String [] args){
        try{
        Factory a=new Factory("/Users/kunkerdthaisong/cn332/hw/about/assigment/brige_factory/configs.json","json");
        System.out.println(a.readConfig("name"));
        } catch (FileNotFoundException e ){
            System.out.println("not found");
        }
        
    }
}
