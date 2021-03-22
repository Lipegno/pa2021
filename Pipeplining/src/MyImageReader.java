import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImageReader {

    public BufferedImage readImage(String path){
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
