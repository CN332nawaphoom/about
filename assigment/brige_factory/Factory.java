import Drawing.Drawing;
import Shape.Shape;

public class Factory{
    private Drawing drawing;
    private Shape[] shapes;
    private String conf_path;

    public Factory(String conf_path){
        read_conf();
    }

    public void read_conf(){
        // TODO read_conf
    }

    public void saveImage(String file_path){
        // TODO saveImage
    }

    public void set_conf_path(String path){
        // TODO set_conf_path
    }
}