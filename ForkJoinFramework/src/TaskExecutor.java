import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TaskExecutor {

    private String path;
    private AudioInputStream audioStream;
    private Clip soundClip;
    private BufferedImage result;
    private String content;

    public void playSong(String path){
        this.path = path;
        try {
            audioStream = AudioSystem.getAudioInputStream(new File(path));
            soundClip = AudioSystem.getClip();
            soundClip.open(audioStream);
            soundClip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage readImage(String path){
        result = null;
        try {
            result = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readFile(String originFile) {
        File original = new File(originFile);
        Scanner reader = null;
        content = "";
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
}
