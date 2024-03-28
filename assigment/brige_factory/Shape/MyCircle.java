package Shape;

public class MyCircle extends MyShape {
    private String line_color;
    private String area_color;

    public MyCircle(String line_color, String area_color){
        this.line_color = line_color;
        this.area_color = area_color;
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

    @Override
    public int[] get_position_data() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get_position_data'");
    }

    @Override
    public void set_position_data(int[] position_data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set_position_data'");
    }

}
