package Shape;

public class MyRectangle extends MyShape {
    private String line_color;
    private String area_color;
    private int[] position_data;

    public MyRectangle(String line_color, String area_color, int[] position_data){
        this.line_color = line_color;
        this.area_color = area_color;
        this.position_data = position_data;
    }

    public int[] get_position_data() {
        return position_data;
    }

    public void set_position_data(int[] position_data) {
        this.position_data = position_data;
    }

    @Override
    public String get_line_color() {
        return line_color;
    }

    @Override
    public String get_area_color() {
        return area_color;
    }

    @Override
    public void set_line_color(String color) {
        line_color = color;
    }

    @Override
    public void set_area_color(String color) {
        area_color = color;
    }

}
