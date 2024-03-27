import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filename = "\\configs.json";
        String filepath = dir + filename;

        DrawingFactory f = new DrawingFactory(filepath);
        try{
            f.read_conf();
            f.saveImage("png", "C:\\Users\\paipa\\Pictures\\test2.png");
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
    }
}