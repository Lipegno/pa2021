import java.util.Scanner;

public class Main {

    private static String content = "";

    private static class LauncherThread extends Thread{
        private static String answers;
        private static String data;
        private LauncherThread(String answerFile, String dataFile){
            this.answers = answerFile;
            this.data    = dataFile;
        }

        public void run(){
            FileReader reader = new FileReader(data);
            reader.start();
            FileSaver saver = new FileSaver(answers,content);
            saver.start();
            try {
                reader.join();
                saver.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        System.out.println(" *****  Sequential Code  *****");
        Scanner sc =  new Scanner(System.in);

        System.out.println("Please insert your name:");
        content += sc.nextLine()+"\n";

        System.out.println("Plase insert your age:");
        content += sc.nextLine()+"\n";

        System.out.println("Where is the user Data:");
        String user_data_file = sc.nextLine();

        System.out.println("Where do you want to save the file:");
        String answers_file = sc.nextLine();

        LauncherThread launcher = new LauncherThread(answers_file,user_data_file);
        launcher.run();
        try {
            launcher.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" *****  Sequential Code  *****");
    }
}
