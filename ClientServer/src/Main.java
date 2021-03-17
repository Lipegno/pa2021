public class Main {

    public static void main(String args[]){
        ServerThread server = new ServerThread(8888);
        server.run();
    }
}
