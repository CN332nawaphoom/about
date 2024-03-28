
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Drawing.Drawing;
import Drawing.DrawingAWT;
import Shape.MyRectangle;
import Shape.MyShape;
import io.github.cdimascio.dotenv.Dotenv;

public class DrawingFactory {
    private Drawing drawing;
    private MyShape[] shapes;
    private String conf_path;

    DrawingFactory(String conf_path) {
        this.conf_path = conf_path;
    }


    public void read_conf() throws FileNotFoundException {
        String filetype = conf_path.split("\\.")[1];
        String drawingEngine;

        switch (filetype.toLowerCase()) {
            case "json":
                String jsonContent = read_to_string(conf_path);
                JSONObject obj = new JSONObject(jsonContent);

                // drawing engine
                drawingEngine = obj.getString("drawingEngine");
                System.out.printf("drawingEngine: %s \n", drawingEngine);

                if (drawingEngine.equalsIgnoreCase("awt")) {
                    System.out.printf("Created drawingEngine: %s \n", drawingEngine);
                    this.drawing = new DrawingAWT(); 
                }

                // shapes
                JSONArray shapesArray = obj.getJSONArray("shapes");
                shapes = new MyShape[shapesArray.length()];

                for (int i = 0; i < shapesArray.length(); i++) {
                    JSONObject shapeObject = shapesArray.getJSONObject(i);

                    // Extract shape properties
                    String typeShape = shapeObject.getString("type");
                    String lineColor = shapeObject.getString("line_color");
                    String areaColor = shapeObject.getString("area_color");

                    if (typeShape.equalsIgnoreCase("Rectangle")) {
                        int x = shapeObject.getInt("x");
                        int y = shapeObject.getInt("y");
                        int width = shapeObject.getInt("width");
                        int height = shapeObject.getInt("height");
                        int[] position_data = {x,y,width,height};

                        MyRectangle rect = new MyRectangle(lineColor, areaColor, position_data);
                        drawing.paintShape(rect, Color.decode(lineColor), Color.decode(areaColor), position_data);
                    }

                    // TODO: Handle other shape types //
                }
                break;
            default:
                throw new FileNotFoundException("Unsupported file filetype: " + filetype);
        }
    }

    private String read_to_string(String file_path) throws FileNotFoundException {
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

    public void saveImage(String format_name, String file_path) {
        SwingUtilities.invokeLater(() -> {
            drawing.saveImage(format_name, file_path);
        });
        
    }

    public void set_conf_path(String path) {
        // TODO set_conf_path
    }
}