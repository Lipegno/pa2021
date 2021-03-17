public class Slave extends Thread {

    private final Thread slaveTask;

    public Slave(Thread slaveTask) {
        this.slaveTask = slaveTask;
    }

    public void run(){
        slaveTask.start();
    }

}
