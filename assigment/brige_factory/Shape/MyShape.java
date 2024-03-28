package Shape;
import java.awt.Color;

public abstract class MyShape {
    public abstract Color get_line_color();
    public abstract void set_line_color(String color);

    public abstract Color get_area_color();
    public abstract void set_area_color(String color);

    public abstract int[] get_position_data();
    public abstract void set_position_data(int[] position_data);


}
