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
        FileWriter encryptedFile = null;
        try {
            encryptedFile = new FileWriter(path);
            encryptedFile.write(content);
            encryptedFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        saveFile(path,content);
    }

}
