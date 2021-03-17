import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReaderThread extends Thread{

    private final String path;

    public BufferedImage getImage() {
        return image;
    }

    private BufferedImage image;
    public ImageReaderThread(String path){
        this.path = path;
    }

    public BufferedImage readImage(String path){
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void run(){
        JFrame frame = new JFrame("PAT4");
        JPanel panel =  new JPanel();
        frame.getContentPane();
        panel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 850);
        frame.setVisible(true);

        this.image = readImage(path);

        ImageIcon icon = new ImageIcon(this.image);
        JLabel l = new JLabel();
        l.setIcon(icon);
        l.setBounds(25,25,this.image.getWidth(),this.image.getHeight());
        panel.add(l);
        frame.add(panel);
    }
}
