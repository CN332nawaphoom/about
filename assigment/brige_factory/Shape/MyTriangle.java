package Shape;
import java.awt.Color;

public class MyTriangle extends MyShape {
    private String line_color;
    private String area_color;
    private int[] position_data = new int[6];

    public MyTriangle(String line_color, String area_color, int[] coordinate_x, int[] coordinate_y){
        this.line_color = line_color;
        this.area_color = area_color;
        for (int i=0;i<3;i++){
            this.position_data[i] = coordinate_x[i];
            this.position_data[i+3] = coordinate_y[i];
        }
        
    }

    @Override
    public Color get_line_color() {
        return Color.decode(line_color);
    }

    @Override
    public Color get_area_color() {
        return Color.decode(area_color);
    }

    @Override
    public void set_line_color(String color) {
        line_color = color;
    }

    @Override
    public void set_area_color(String color) {
        area_color = color;
    }

    @Override
    public int[] get_position_data() {
        return position_data;
       
    }

    @Override
    public void set_position_data(int[] position_data) {
        this.position_data = position_data;
        
    }
}

    
