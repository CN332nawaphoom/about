import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;

public class Factory {

    private final String filePath;
    private JSONObject config;
    private String content;

    public Factory(String path, String type) throws FileNotFoundException {
        this.filePath = path;
        switch (type.toLowerCase()) {
            case "json":
                try (FileReader reader = new FileReader(filePath)) {
                    StringBuilder sb = new StringBuilder();
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        sb.append((char) ch);
                    }
                    String jsonContent = sb.toString();
                    this.config = new JSONObject(jsonContent);
                } catch (IOException | JSONException e) {
                    throw new FileNotFoundException("Error reading file: " + filePath + ". Cause: " + e.getMessage());
                }
                break;
            case "txt":
                try (FileReader reader = new FileReader(filePath)) {
                    StringBuilder sb = new StringBuilder();
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        sb.append((char) ch);
                    }
                    this.content = sb.toString();
                } catch (IOException e) {
                    throw new FileNotFoundException("Error reading file: " + filePath + ". Cause: " + e.getMessage());
                }
                break;
                case "ini":
                    File file=new File(filePath);
                    String path_=file.getParent();
                    String name=file.getName();
                    Dotenv dotenv = Dotenv.configure().directory(path_).filename(name).load();
                    this.content = dotenv.get("name");
                    break;
            default:
                throw new FileNotFoundException("Unsupported file type: " + type);
        }
    }

    public String readConfig(String key) {
        if (config != null) {
            return config.getString(key);
        } else {
            return this.content;
        }
    }

}
