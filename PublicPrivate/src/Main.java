import java.io.IOException;
import java.security.PrivateKey;

public class Main {

    public static void main(String[] args){
        KeyGenerator my_generator = new KeyGenerator(1024);
        my_generator.generateKeys();
        my_generator.saveKey("/Users/filipequintal/IdeaProjects/PublicPrivate/src/public",
                my_generator.getPublic_k().getEncoded());
        my_generator.saveKey("/Users/filipequintal/IdeaProjects/PublicPrivate/src/private",
                my_generator.getPrivate_k().getEncoded());

        try {
            System.in.read();
        } catch (IOException e) {

        }
        PrivateKey key = my_generator.getPrivate_k();
        String msg = my_generator.decryptMessage("/Users/filipequintal/IdeaProjects/PublicPrivate/src/message",key);
        System.out.println("MENSAGEM: "+msg);
    }
}
