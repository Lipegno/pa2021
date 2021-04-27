import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MACSocketServer extends Thread {

    private DataInputStream in;
    private ServerSocket server;
    private Socket socket;
    private int port;
    public MACSocketServer(int port) {
        this.port = port;
    }

    public void run(){
        try {
            server = new ServerSocket(port);
            System.out.println("Started Server Socket");
            socket = server.accept();
            System.out.println("Receiving a message");
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            String temp = "";
            String line = "";
            while(!temp.equals("FIM")){
                temp = in.readUTF();
                if(!temp.equals("FIM"))
                  line = temp;
            }
            // separar o mac da mensagem
            String mac = line.split("MAC")[1];
            String msg = line.split("MAC")[0];
            // Criar um objeto do tipo mensagem
            Message myMsg = new Message("Mas2142SS!±");
            // Gerar o mac
            myMsg.createMyMACandMessage(msg);

            //verificar se o mac corresponde
            if(mac.endsWith(new String(myMsg.mac_result))){
                System.out.println("#     MENSAGEM VÁLIDA     #");
            }else{
                System.out.println("#   MENSAGEM NÃO VÁLIDA   #");
            }
            // close connection
            socket.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
