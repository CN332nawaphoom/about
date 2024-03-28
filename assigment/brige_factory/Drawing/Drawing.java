package Drawing;
import Shape.MyShape;

public interface Drawing {
    public void saveImage(String format_name, String file_path);

    public void paintShape(MyShape shape);
}
