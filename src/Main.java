import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main{

    private static class ImageChangeMaster implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals("new_image")){
                System.out.println("TENHO UMA IMAGEM PRONTA DO WORKER");
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("PATP2");
        JPanel panel =  new JPanel();
        frame.getContentPane();
        panel.setLayout(null);
        //åframe.getContentPane().add(new Test());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 830);
        frame.setVisible(true);

        MyImageReader reader = new MyImageReader();
        final BufferedImage image = reader.readImage("image.png");

        ImageTransformer transformer = new ImageTransformer();


        JButton b=new JButton("Transform image");
        b.setBounds(850,100,220,60);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            /*
            Add code to split the program into tasks
             */
                BufferedImage temp = image;// duplicate to be manipulated here
                //temp = transformer.removeReds(image,temp.getWidth(),0,0);
                ImageIcon icon = new ImageIcon(temp);
                JLabel l = new JLabel();
                l.setIcon(icon);
                l.setBounds(25,25,temp.getWidth(),temp.getHeight());
                panel.add(l);
                frame.add(panel);
                SwingUtilities.updateComponentTreeUI(frame);

            }
        });



        frame.add(b);

        //frame.getContentPane().add(button);
        frame.repaint();


    }

}
