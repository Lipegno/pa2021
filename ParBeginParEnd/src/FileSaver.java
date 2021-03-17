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
            System.out.println("File saved!!");
        } catch (IOException e) {
            System.out.println("Error saving File");
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println(" ******  Saving File *****");
        saveFile(path,content);
    }

}
