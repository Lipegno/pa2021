import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    private DataInputStream in;
    private PrintWriter out;
    private ServerSocket server;
    private Socket socket;
    private int port;
    public ServerThread(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        while (true){
            try {
                System.out.println("Accepting Data");
                socket = server.accept();
                in     = new DataInputStream(socket.getInputStream());
                out    = new PrintWriter(socket.getOutputStream(),true);
                String message= in.readUTF();
                System.out.println("***** "+message+" *****");
                out.println(message.toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
