package Drawing;

import java.awt.Color;

import Shape.MyRectangle;
import Shape.MyShape;

public interface Drawing {
    public void saveImage(String format_name, String file_path);

    public void paintShape(MyShape shape, Color lineColor, Color areaColor, int[] position_data);
}
