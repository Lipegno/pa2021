import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ProducerThread extends Thread {

    private Semaphore sem;
    private int id;
    private int counter;
    ArrayList<String> buffer;

    public ProducerThread(int id, ArrayList<String> buffer, Semaphore s){
        this.id = id;
        this.buffer = buffer;
        this.sem  = s;
    }

    public void run(){
        for(int i=0;i<20;i++){
            try {
                sleep(2500); // make this action a bit slow
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(sem.tryAcquire(10,TimeUnit.SECONDS)) {
                    buffer.add("Message " + i + " from Producer " + id);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                sem.release();
            }
        }
    }

}
