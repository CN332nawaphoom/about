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
import Shape.MyCircle;
import Shape.MyRectangle;
import Shape.MyTriangle;
import Shape.MyShape;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile;
import org.ini4j.Profile.Section;

public class DrawingFactory {
    private Drawing drawing;
    //private MyShape[] shapes;
    private String conf_path;

    DrawingFactory(String conf_path) {
        this.conf_path = conf_path;
    }

    private MyShape createMyShape_fromJSONObject(JSONObject shapeObject,String typeShape,String lineColor, String areaColor){
        MyShape myshape;

        if (typeShape.equalsIgnoreCase("Rectangle")) {
            int x = shapeObject.getInt("x");
            int y = shapeObject.getInt("y");
            int width = shapeObject.getInt("width");
            int height = shapeObject.getInt("height");
            int[] position_data = {x,y,width,height};

            myshape = new MyRectangle(lineColor, areaColor, position_data);
        }
        else if (typeShape.equalsIgnoreCase("Circle")) {
            int x = shapeObject.getInt("x");
            int y = shapeObject.getInt("y");
            int radius = shapeObject.getInt("radius");
            int[] position_data = {x,y,radius};

            myshape = new MyCircle(lineColor, areaColor, position_data);
        }

        else if (typeShape.equalsIgnoreCase("Triangle")) {
            int[] coordinate_x = shapeObject.getJSONArray("x").toList().stream().mapToInt(o -> (int) o).toArray();
            int[] coordinate_y = shapeObject.getJSONArray("y").toList().stream().mapToInt(o -> (int) o).toArray();
            int[] position_data = new int[6];
            System.arraycopy(coordinate_x, 0, position_data, 0, coordinate_x.length);
            System.arraycopy(coordinate_y, 0, position_data, coordinate_x.length, coordinate_y.length);

            
            myshape = new MyTriangle(lineColor, areaColor, coordinate_x,coordinate_y);
        }else{
            // should have throw an exception
            return null;
        }
        return myshape;
    }

    private MyShape createMyShape_from_ini(){
        // TODO later
        return null;
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
                //

                // shapes
                JSONArray shapesArray = obj.getJSONArray("shapes");

                for (int i = 0; i < shapesArray.length(); i++) {
                    JSONObject shapeObject = shapesArray.getJSONObject(i);

                    // Extract shape properties
                    String typeShape = shapeObject.getString("type");
                    String lineColor = shapeObject.getString("line_color");
                    String areaColor = shapeObject.getString("area_color");

                    MyShape shape = createMyShape_fromJSONObject(shapeObject,typeShape,lineColor,areaColor);
                    SwingUtilities.invokeLater(() -> {
                        drawing.paintShape(shape);
                    });
                    
                }

                // end case "json"
                break;


            
            case "ini":
                try{
                    System.out.println(conf_path);
                    Ini ini = new Ini(new File(conf_path));

                    // drawing engine
                    drawingEngine = ini.get("drawingSettings", "drawingEngine", String.class);
                    if (drawingEngine.equalsIgnoreCase("awt")) {
                        System.out.printf("Created drawingEngine: %s \n", drawingEngine);
                        this.drawing = new DrawingAWT(); 
                    }

                    // get shapes from ini
                    String shapesString = ini.get("shapeArray", "shapes");
                    String[] shapesStringArray = shapesString.split(", ");
                    for(int i=0; i < shapesStringArray.length; i++){
                        String shape_name = "shape" + (i+1);
                        
                        String type = ini.get("shapes", shape_name + ".type");
                        System.out.println(type);

                        // TODO get other field based on its type and create shape

                        //


                        // Call this function when you get "MyShape shape" 
                        // SwingUtilities.invokeLater(() -> {
                        //     drawing.paintShape(shape);
                        // });
                    }
                    

                }

                catch(InvalidFileFormatException err){
                    System.out.println(err);
                }catch(IOException err){
                    System.out.println(err);
                }
                break;
            //

            // TODO others filetype
            
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