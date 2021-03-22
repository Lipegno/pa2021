import java.io.File;
import java.util.Scanner;

public class FileReaderThread extends Thread {
    private final String path;
    private String file_content;

    public FileReaderThread(String path){
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
        System.out.println("*** FILE READ ***");
        return content;
    }

    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        file_content = readFile(path);
        System.out.println(file_content);

    }
}
