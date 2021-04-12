import javax.crypto.Cipher;

public class Main {
    public static void main(String[] args){
        PAAlgorithm.encryptFile("/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/textoSecreto.txt","/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/TextoEncriptado.txt");
        PAAlgorithm.decryptFile("/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/TextoEncriptado.txt");

        //decrypt

        //DESAESAlgorithms.initDESChipher("NewDumbKey", Cipher.ENCRYPT_MODE);
        //DESAESAlgorithms.encryptDESFile("/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/textoSecreto.txt","/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/TextoEncriptadoDES");
        //DESAESAlgorithms.initDESChipher("NewDumbKey", Cipher.DECRYPT_MODE);
        //DESAESAlgorithms.decryptDESFile("/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/TextoEncriptadoDES");

        //DESAESAlgorithms.initAESChiper();
        //DESAESAlgorithms.encryptAESFile("/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/textoSecreto.txt","/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/TextoEncriptadoAES");
        //DESAESAlgorithms.decryptAESFile("/Volumes/GoogleDrive/My Drive/aulas/2020/PA/T/Aula 6/TextoEncriptadoAES");
    }
}
