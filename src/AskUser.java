import java.util.Scanner;

public class AskUser extends Thread{

    private final String message;
    private String answer;

    public AskUser(String message){
        this.message = message;
    }

    private String askForText(String prompt){
        String answer = "";
        Scanner sc =  new Scanner(System.in);
        System.out.println(prompt);
        answer = sc.nextLine();
        return answer;
    }

    public String getAnswer(){
        return answer;
    }

    @Override
    public void run() {
        answer =  askForText(this.message);
    }
}