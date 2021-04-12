import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.Scanner;

public class PAAlgorithm {

    private static String key = "!#$%&/()=?*`ª^P^_:;MNBVCDS><";
    private static Cipher ci;
    private static SecretKey skey;
    private static IvParameterSpec ivspec;
    private static Cipher des_cipher;
    private static SecretKey deskey;



    public static void encryptFile(String originFile, String destinationFile){
        File original = new File(originFile);
        Scanner reader = null;
        try {
            reader = new Scanner(original);
            String encryptedContent = "";
            while(reader.hasNextLine()){
                String input = reader.nextLine();
                encryptedContent = encryptedContent+"\n"+encrypt(input);
            }
            //System.out.println(encryptedContent);
            FileWriter encryptedFile = new FileWriter(destinationFile);
            encryptedFile.write(encryptedContent);
            encryptedFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String encrypt(String block){
        String encryptedBlock="";

       // System.out.println(encryptedBlock);
        return encryptedBlock;
    }

    public static void decryptFile(String encryptedFile){
        File original = new File(encryptedFile);
        Scanner reader = null;
        try {
            reader = new Scanner(original);
            String plainTextContent = "";
            while(reader.hasNextLine()){
                String input = reader.nextLine();
                plainTextContent = plainTextContent+"\n"+decrypt(input);
            }
            System.out.println(plainTextContent);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String decrypt(String block){
        String decryptedBlock="";

        return decryptedBlock;
    }


}
