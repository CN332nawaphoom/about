package Drawing;

import Shape.Shape;

public interface Drawing {
    public void draw(Shape s);

    public void fill(Shape s);

    public void saveImage(String format_name, String file_path);
}
