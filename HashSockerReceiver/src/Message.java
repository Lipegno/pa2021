import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Message {

    private KeyGenerator keygen;
    private SecureRandom secure;
    private SecretKey secret_key;
    private Mac mac;
    byte[] mac_result;

    public Message(String key){
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            secret_key = skf.generateSecret(dks);
            mac = Mac.getInstance("HmacSHA256");
            mac.init(secret_key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    byte[] createMyMACandMessage(String msg){
        byte[] msg_bytes = msg.getBytes();
        byte[] macResult = mac.doFinal(msg_bytes);

        System.out.println(new String(macResult));
        mac_result = macResult;
        return macResult;
    }
}
