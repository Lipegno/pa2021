import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

public class KeyGenerator {

    private KeyPairGenerator generator;
    private KeyPair pair;
    private PrivateKey private_k;
    private PublicKey public_k;

    public KeyGenerator(int size){
        try {
            this.generator = KeyPairGenerator.getInstance("RSA");
            this.generator.initialize(size);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void generateKeys(){
        this.pair = this.generator.generateKeyPair();
        this.setPrivate_k(pair.getPrivate());
        this.setPublic_k(pair.getPublic());
    }

    public PrivateKey getPrivate_k() {
        return private_k;
    }

    public void saveKey(String file,byte[] key){
        try{
            FileOutputStream out = new FileOutputStream(file);
            out.write(key);
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String decryptMessage(String messageFile, PrivateKey key){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] encryptedContent = Files.readAllBytes(Paths.get(messageFile));
            byte[] msg = cipher.doFinal(encryptedContent);
            return  new String(msg,"UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "ERROR REATING CIPHER!!";
    }

    public PublicKey getPublic_k() {
        return public_k;
    }

    public void setPublic_k(PublicKey public_k) {
        this.public_k = public_k;
    }

    public void setPrivate_k(PrivateKey private_k) {
        this.private_k = private_k;
    }

}
