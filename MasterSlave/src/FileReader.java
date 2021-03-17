import java.io.File;
import java.util.Scanner;

public class FileReader extends Thread {
    private final String path;
    private String file_content;

    public FileReader(String path){
        this.path = path;
    }

    public String getContent(){
        return file_content;
    }

    private String readFile(String originFile) {
        File original = new File(originFile);
        Scanner reader = null;
        String content = "";
        try {
            reader = new Scanner(original);
            while (reader.hasNextLine()) {
                String input = reader.nextLine();
                content = content + "\n" + input;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public void run(){
        file_content = readFile(path);
        System.out.println(file_content);
    }
}
