package Drawing;

import Shape.MyShape;

public interface Drawing {
    public void paint(MyShape s, String line_color, String area_color);

    public void saveImage(String format_name, String file_path);
}
