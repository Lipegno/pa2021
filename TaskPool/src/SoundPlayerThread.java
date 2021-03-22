import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayerThread extends Thread {
    private AudioInputStream audioStream;
    private String path;
    private Clip soundClip;

    public SoundPlayerThread(String path){
        this.path = path;
        try {
            audioStream = AudioSystem.getAudioInputStream(new File(path));
            soundClip = AudioSystem.getClip();
            soundClip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*** STARTED PLAYING THE CLIP ***");
        soundClip.start();
    }
}
