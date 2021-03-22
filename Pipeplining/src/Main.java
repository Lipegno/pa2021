import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String args[]){
        //sets up the frame
        JFrame frame = new JFrame("PipeliningExample");
        JPanel panel =  new JPanel();
        frame.getContentPane();
        panel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 830);
        frame.setVisible(true);
        //reads the image
        MyImageReader reader = new MyImageReader();
        final BufferedImage image = reader.readImage("image.jpg");
        try {
            ImageTransformer filter1 =  new ImageTransformer(1,image);
            filter1.start();
            filter1.join();
            ImageTransformer filter2 =  new ImageTransformer(1,filter1.getImg());
            filter2.start();
            filter2.join();
            ImageTransformer filter3 =  new ImageTransformer(1,filter2.getImg());
            filter3.start();
            filter3.join();
            ImageTransformer filter4 = new ImageTransformer(1,filter3.getImg());
            filter4.start();
            filter4.join();
            ImageTransformer filter5 = new ImageTransformer(1,filter4.getImg());
            filter5.start();
            filter5.join();
            ImageTransformer filter6 = new ImageTransformer(1,filter5.getImg());
            filter6.start();
            filter6.join();
            ImageIcon icon = new ImageIcon(filter6.getImg());
            JLabel l = new JLabel();
            l.setIcon(icon);
            l.setBounds(25,25,filter1.getImg().getWidth(),filter1.getImg().getHeight());
            panel.add(l);
            frame.add(panel);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
