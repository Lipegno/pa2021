import java.io.FileWriter;
import java.io.IOException;

public class FileSaver extends Thread{

    private final String path;
    private final String content;
    public FileSaver(String path, String content){
        this.path = path;
        this.content = content;
    }

    private void saveFile(String path, String content){
        FileWriter file = null;
        try {
            file = new FileWriter(path);
            file.write(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        saveFile(path,content);
    }

}
