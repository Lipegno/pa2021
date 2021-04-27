import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketSender {

    private  Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    public SocketSender(int port){
        try {
            socket = new Socket("127.0.0.1",port);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            Message myMsg  = new Message("Mas2142SS!±");
            myMsg.createMyMACandMessage(message);
            message = message+"MAC"+new String(myMsg.mac_result);
            output.writeUTF(message);
            output.writeUTF("FIM");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
