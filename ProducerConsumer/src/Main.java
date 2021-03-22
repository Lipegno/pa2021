import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]){
        ArrayList<String> buffer = new ArrayList<>(); // shared memory
        ArrayList<ProducerThread> producers = new ArrayList<>(); //producers
        Semaphore buffer_sem = new Semaphore(1);
        for(int i=0;i<5;i++){
            ProducerThread p = new ProducerThread(i,buffer,buffer_sem);
            p.start();
            producers.add(p);
        }
        ConsumerThread c = new ConsumerThread(1,buffer,buffer_sem);
        c.start();

        ConsumerThread c2 = new ConsumerThread(2,buffer,buffer_sem);
        c2.start();

        ConsumerThread c3 = new ConsumerThread(3,buffer,buffer_sem);
        c3.start();


    }
}
