public class TimeCalculatorThread extends Thread{

    private long milis;

    public TimeCalculatorThread(){
        milis = System.currentTimeMillis();
    }

    public void run(){
        System.out.println("**** I've waiting "+(System.currentTimeMillis()-milis)+" to execute *** ");
    }

}
