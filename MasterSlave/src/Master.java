public class Master {

    public static void main(String args[]){
        FileReader myReader = new FileReader("/Users/filipequintal/Desktop/teste.txt");
        ImageReaderThread image_reader = new ImageReaderThread("/Users/filipequintal/Desktop/image.jpg");
        SoundPlayerThread mySoundPlayer =  new SoundPlayerThread("ImperialMarch60.wav");

        Slave s1 = new Slave(myReader);
        Slave s2 = new Slave(image_reader);
        Slave s3 = new Slave(mySoundPlayer);

        s1.start();
        s2.start();
        s3.start();

    }
}
