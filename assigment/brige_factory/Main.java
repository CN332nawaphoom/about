import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String configName = "configs.json";
        String filepath = dir + "\\configs\\" + configName;
        
        // Create directory for images if it does not exist
        String imgDirPath = dir + "\\imgs";
        File imgDir = new File(imgDirPath);
        if (!imgDir.exists()) {
            boolean success = imgDir.mkdirs();
            if (!success) {
                System.out.println("Failed to create directory for images.");
                return;
            }
        }

        DrawingFactory f = new DrawingFactory(filepath);
        try {
            f.read_conf();

            String imgFileName = "test2.png";
            filepath = dir + "\\imgs\\" + imgFileName;
            f.saveImage("png", filepath);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}