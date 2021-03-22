import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageTransformer extends Thread{

    private final int id;

    public BufferedImage getImg() {
        return img;
    }

    private BufferedImage img;

    public ImageTransformer(int id, BufferedImage img) {
        this.id = id;
        this.img = img;
    }


    public BufferedImage applyFilter(BufferedImage image,int size, int offset_x,int offset_y){
        int width   = size;//offset;//image.getWidth();
        int height = size;//offset;//image.getHeight();
        Color c;
        BufferedImage resultado = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                //System.out.println("Working on "+(i+offset_x)+"   "+(j+offset_y));
                c = new Color(image.getRGB(i+offset_x,j+offset_y));
                int r = Math.round(c.getRed()*0.8f);
                int g = Math.round(c.getGreen()*0.8f);
                int b = Math.round(c.getBlue()*0.8f);
                resultado.setRGB(i,j,new Color(r,g,b).getRGB());
            }

        }
        System.out.println("Applied the filter");
        return resultado;
    }

    public void run(){
        this.img = applyFilter(img,img.getWidth(),0,0);
    }
}
