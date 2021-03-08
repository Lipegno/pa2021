import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        try {
            System.out.println("Starting Main");
            //T3
            FileReader frt =  new FileReader("/Users/filipequintal/Desktop/index.html");
            frt.start();
            frt.join();
            String file_content =  frt.getContent();

            //T5
            HTMLStripper stripper =  new HTMLStripper(file_content);
            stripper.start();
            //T1 (NEW T1 WITH THE ALL THE TASKS IN SEQUENCE) A nova t1 com todas as tarefas em sequencia
            UserInputThread uit = new UserInputThread();
            uit.start();

            uit.join();
            stripper.join();

            String striped_content = stripper.getStripped_content();
            String name =uit.getTitle();
            String data =uit.getData();
            String image_url = uit.getImage_url();

            //T4
            EditContent edt1 = new EditContent("<h1> "+name+" </h1> <h1> "+data+" </h1>",striped_content,1);
            edt1.start();
            edt1.join();
            striped_content = edt1.getFile();
            //T4
            EditContent edt2 = new EditContent("<img src="+image_url+"></img>",striped_content,0);
            edt2.start();
            edt2.join();
            striped_content = edt2.getFile();
            //T2
            FileSaver svt = new FileSaver("ficheiroHMTL.html",striped_content);
            svt.start();
            svt.run();
            svt.join();

        //striped_content = addContentEnd("<img src="+image_url+"></img>",striped_content);
            // saveFile("ficheiroTontoTeste.html",striped_content);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
