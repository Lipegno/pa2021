public class EditContent extends Thread{

    private static final int START = 1;
    private static final int END = 0;
    private final String content;
    private String file;
    private final int type;


    public EditContent(String content,String file, int start_end){
        this.content = content;
        this.file    = file;
        this.type    = start_end;
    }

    private String addContentStart(String content, String file){
        file = content+file;
        return file;
    }

    private String addContentEnd(String content, String file){
        file = file+content;
        return file;
    }

    public String getFile(){
        return this.file;
    }

    @Override
    public void run() {
        if(type==START){
            this.file = addContentStart(this.content,this.file);
        }else{
            this.file = addContentEnd(this.content,this.file);
        }
    }
}