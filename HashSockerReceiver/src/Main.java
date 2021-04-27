public class Main {

    public static void main(String[] args){
        MACSocketServer s = new MACSocketServer(8888);
        s.start();
        try {
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
