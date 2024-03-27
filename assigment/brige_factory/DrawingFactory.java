
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Drawing.Drawing;
import Drawing.DrawingAWT;
import Shape.Shape;
import io.github.cdimascio.dotenv.Dotenv;


public class DrawingFactory{
    private Drawing drawing;
    private Shape[] shapes;
    private String conf_path;

    DrawingFactory(String conf_path){
        this.conf_path = conf_path;
    }

    public void read_conf() throws FileNotFoundException{
        String type = conf_path.split("\\.")[1];
        String drawingEngine;
        Shape[] shapes;

        switch (type.toLowerCase()) {
            case "json":
                String jsonContent = read_to_string(conf_path);
                JSONObject obj = new JSONObject(jsonContent);

                // drawing engine
                drawingEngine = obj.getString("drawingEngine");
                System.out.printf("drawingEngine: %s \n", drawingEngine);

                if (drawingEngine.equalsIgnoreCase("awt")){
                    this.drawing = new DrawingAWT(); 
                }

                // shapes
                JSONArray shapesArray = obj.getJSONArray("shapes");
                // TODO shapes creation loop //

                break;
            default:
                throw new FileNotFoundException("Unsupported file type: " + type);
        }
    }

    private String read_to_string(String file_path) throws FileNotFoundException{
        try (FileReader reader = new FileReader(file_path)) {
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            String content = sb.toString();
            return content;

        } catch (IOException | JSONException e) {
            throw new FileNotFoundException("Error reading file: " + file_path + ". Cause: " + e.getMessage());
        }
    }

    public void saveImage(String file_path){
        // TODO saveImage
    }

    public void set_conf_path(String path){
        // TODO set_conf_path
    }
}