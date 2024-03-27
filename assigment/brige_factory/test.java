// Main.java

import java.io.FileNotFoundException;

public class test {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filename = "\\configs.json";
        String filepath = dir + filename;

        DrawingFactory f = new DrawingFactory(filepath);
        try{
            f.read_conf();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
    }
    
}