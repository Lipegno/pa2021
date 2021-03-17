import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String args[]){

        Semaphore   sem = new Semaphore(1);

        ClientThread client = new ClientThread(8888,1,sem);
        client.start();

        ClientThread client2 = new ClientThread(8888,2,sem);
        client2.start();

        ClientThread client3 = new ClientThread(8888,3,sem);
        client3.start();

        ClientThread client4 = new ClientThread(8888,4,sem);
        client4.start();
    }
}
