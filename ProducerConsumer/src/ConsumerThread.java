import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConsumerThread extends Thread {

    private final int id;
    ArrayList<String> buffer;
    Semaphore sem;
    public ConsumerThread(int id, ArrayList<String> buffer, Semaphore s){
        this.id = id;
        this.buffer = buffer;
        this.sem = s;
    }

    private void printBuffer(){
        System.out.println("********** printing buffer content **********");
        for(int i=0;i<buffer.size();i++){
            System.out.println("*   "+buffer.get(i));
        }
    }

    public void run(){
        for(int i=0;i<200;i++){
            try {
                sleep(1000); // make this action a bit slow
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(sem.tryAcquire(10, TimeUnit.SECONDS)) {
                    if (buffer.size() > 0) {
                        printBuffer();
                        System.out.println("Consumer " + id + ", Will consume : " + buffer.get(buffer.size() - 1));
                        buffer.remove(buffer.size() - 1); // POP
                        printBuffer();
                    } else {
                        System.out.println("Consumer " + id + " cannot consumer buffer is empty");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                sem.release();
            }
        }

    }

}