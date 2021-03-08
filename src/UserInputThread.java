public class UserInputThread extends Thread{


    private String image_url;
    private String data;
    private String title;

    public String getImage_url() {
        return image_url;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void run(){
        try {
            //T1
            AskUser dataT = new AskUser("Introduza a data: ");
            dataT.start();
            dataT.join();
            data =dataT.getAnswer();
            //T1
            AskUser titleT = new AskUser("Introduza o título: ");
            titleT.start();
            titleT.join();
            title =titleT.getAnswer();
            //T1
            AskUser imageT = new AskUser("Introduza o caminho do ficheiro de Imagem:");
            imageT.start();
            imageT.join();
            image_url =imageT.getAnswer();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
