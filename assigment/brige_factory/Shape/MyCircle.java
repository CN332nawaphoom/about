package Shape;
import java.awt.Color;


public class MyCircle extends MyShape {
    private String line_color;
    private String area_color;
    private int[] position_data; //[x,y,radius]

    public MyCircle(String line_color, String area_color,int[] position_data){
        this.line_color = line_color;
        this.area_color = area_color;
        this.position_data = position_data;
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
