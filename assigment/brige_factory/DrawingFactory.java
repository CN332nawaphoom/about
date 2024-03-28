import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Drawing.Drawing;
import Drawing.DrawingAWT;
import Shape.MyCircle;
import Shape.MyRectangle;
import Shape.MyTriangle;
import Shape.MyShape;

public class DrawingFactory {
    private Drawing drawing;
    //private MyShape[] shapes;
    private String conf_path;

    DrawingFactory(String conf_path) {
        this.conf_path = conf_path;
    }


    public void read_conf() throws FileNotFoundException {
        String filetype = conf_path.split("\\.")[1];
        String drawingEngine;
        MyShape[] shapes;


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
                        drawing.paintShape(rect);
                    }
                    else if (typeShape.equalsIgnoreCase("Circle")) {
                        int x = shapeObject.getInt("x");
                        int y = shapeObject.getInt("y");
                        int radius = shapeObject.getInt("radius");
                        int[] position_data = {x,y,radius};

                        MyShape cir = new MyCircle(lineColor, areaColor, position_data);
                        SwingUtilities.invokeLater(() -> {
                            // Call the paint method on the EDT
                            drawing.paintShape(cir);
                        });
                        
                    }

                    else if (typeShape.equalsIgnoreCase("Triangle")) {
                        int[] coordinate_x = shapeObject.getJSONArray("x").toList().stream().mapToInt(o -> (int) o).toArray();
                        int[] coordinate_y = shapeObject.getJSONArray("y").toList().stream().mapToInt(o -> (int) o).toArray();
                        int[] position_data = new int[6];
                        System.arraycopy(coordinate_x, 0, position_data, 0, coordinate_x.length);
                        System.arraycopy(coordinate_y, 0, position_data, coordinate_x.length, coordinate_y.length);

                        
                        MyShape tri = new MyTriangle(lineColor, areaColor, coordinate_x,coordinate_y);
                        SwingUtilities.invokeLater(() -> {
                            // Call the paint method on the EDT
                            drawing.paintShape(tri);
                        });
                        
                    }

                    
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
        
    }
}