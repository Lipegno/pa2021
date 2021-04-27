public class Main {

    public static void main(String arg[]){
        //System.out.println("COISAS AQUI");
        String my_message_text = "Mensagem Super jjj Importante aqui, não pode ser alterada";
        //Message myMsg  = new Message("Mas2142SS!±");
        //Message myMsg2 = new Message("Mas2142SS!±");
        //myMsg.createMyMACandMessage(my_message_text);
        //myMsg2.createMyMACandMessage(my_message_text);
        //byte[] val1 = myMsg.mac_result;
        //byte[] val2 = myMsg2.mac_result;
        //System.out.println(new String(val1).equals(new String(val2)));

        SocketSender s = new SocketSender(8888);
        //my_message_text = my_message_text+"MAC"+new String(val1);

        s.sendMessage(my_message_text);
        s.sendMessage("FIM");

        s.closeSocket();
    }
}
