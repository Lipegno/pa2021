import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        try {
            System.out.println("Starting Main");
            FileReader frt =  new FileReader("/Users/filipequintal/Desktop/index.html");
            frt.start();
            frt.join();
            String file_content =  frt.getContent();

            HTMLStripper stripper =  new HTMLStripper(file_content);
            stripper.start();
            stripper.join();
            String striped_content = stripper.getStripped_content();

            AskUser nameT = new AskUser("Introduza a data");
            nameT.start();
            nameT.join();
            String name =nameT.getAnswer();

            AskUser dataT = new AskUser("Introduza o seu nome");
            dataT.start();
            dataT.join();
            String data =dataT.getAnswer();

            EditContent edt1 = new EditContent("<h1> "+name+" </h1> <h1> "+data+" </h1>",striped_content,1);
            edt1.start();
            edt1.join();
            striped_content = edt1.getFile();

            AskUser imageT = new AskUser("Introduza o caminho do ficheiro:");
            imageT.start();
            imageT.join();
            String image_url =imageT.getAnswer();


            EditContent edt2 = new EditContent("<img src=\""+image_url+"\"></img>",striped_content,0);
            edt2.start();
            edt2.join();
            striped_content = edt2.getFile();

            FileSaver svt = new FileSaver("ficheiroHMTL.html",striped_content);
            svt.start();
            svt.run();
            svt.join();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
