import java.io.*;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ClientThread extends Thread{
    private final int port;
    private final int id;
    private final Semaphore sem;
    private DataOutputStream out;
    private BufferedReader in;
    private Socket socket;

    public ClientThread(int port, int id, Semaphore sem){
        this.port = port;
        this.id =id;
        this.sem = sem;
    }

    public void run(){
        try {
            int i = 0;
            while(true){
                System.out.println("Sending Data");
                try {
                    if(sem.tryAcquire(5, TimeUnit.SECONDS)) {
                        socket = new Socket("localhost", port);
                        out = new DataOutputStream(socket.getOutputStream());
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out.writeUTF("My message number " + i + " to the server " + "I'm " + id);
                        String response;
                        response = in.readLine();
                        System.out.println("From Server " + response);
                        out.flush();
                        socket.close();
                    }
                }finally {
                     sem.release();
                     System.out.println("Semaphore Released!!");
                }
                sleep(10);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
