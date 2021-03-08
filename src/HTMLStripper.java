public class HTMLStripper extends Thread{

    private final String html_content;
    private String stripped_content;

    public HTMLStripper(String html){
        this.html_content =  html;
    }

    private String stripHTML(String content){
        String parsed_content="";
        for(int i=0;i<content.length();i++){
            if(content.charAt(i)=='<'){
                i++;
                while(content.charAt(i)!='>')
                    i++;
            }else{
                parsed_content=parsed_content+content.charAt(i);
            }
        }
        return parsed_content;
    }

    public String getStripped_content(){
        return stripped_content;
    }

    @Override
    public void run() {
        stripped_content = stripHTML(html_content);
    }
}