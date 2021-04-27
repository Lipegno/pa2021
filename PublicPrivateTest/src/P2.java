import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class P2 {

    private Cipher cipher;

    private PublicKey getPublicKey(String path){
        try{
            byte[] key_binary = Files.readAllBytes(new File(path).toPath());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(key_binary);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(spec);
        }catch (IOException e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] encryptMessage(String message, PublicKey key){
        try {
            this.cipher = Cipher.getInstance("RSA");
            this.cipher.init(Cipher.ENCRYPT_MODE, key);
            System.out.println("Lenght : " + message.getBytes("UTF-8").length );
            return cipher.doFinal(message.getBytes("UTF-8"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void saveEncriptedMessage(byte[] message, String filename){
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(message);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        P2 m = new P2();
        String content = "Example of a message to be saved in the file, it cannot be to big"+'\n'+"Teste"
                +'\n'+" If the file is too big there could be a problem here";
        PublicKey public_key = m.getPublicKey("/Users/filipequintal/IdeaProjects/PublicPrivate/src/public");
        byte[] message_binary = m.encryptMessage(content, public_key);
        m.saveEncriptedMessage(message_binary,"/Users/filipequintal/IdeaProjects/PublicPrivate/src/message");

    }
}
