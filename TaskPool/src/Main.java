import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String args[]){
        Runnable r1 = new FileReaderThread("/Users/filipequintal/Desktop/index.html");
        Runnable r2 = new ImageReaderThread("/Users/filipequintal/Desktop/image.jpg");
        Runnable r3 = new SoundPlayerThread("ImperialMarch60.wav");
        Runnable r4 = new TimeCalculatorThread();
        //test add the same runnable several times with some identifier to ckeck how the threapool works
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        //we might neet to add a all of this in a different thread that we can "join" before continuiing

    }
}
