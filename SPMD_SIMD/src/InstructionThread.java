public class InstructionThread extends Thread {
    public static final int  OPERATION_AVERAGE = 1;
    public static final int  OPERATION_SMALLER = 2;
    public static final int  OPERATION_BIGGER = 3;

    private final double[] values;
    private final int operation;
    private final int size;

    private double average;

    public double getAverage() {
        return average;
    }

    public double getSmaller() {
        return smaller;
    }

    public double getBigger() {
        return bigger;
    }

    private double smaller;
    private double bigger;

    public InstructionThread(double[] matrix, int op, int size) {
        this.values = matrix;
        this.operation = op;
        this.size = size;
    }

    private double smallerValue(double[] v, int size){
        double smaller = v[0];
        for(int i=0;i<size;i++){
            smaller = v[0];
        }
        System.out.println("** OPERATION SMALLER RESULT : "+smaller);
        return smaller;
    }

    private double biggerValue(double[] v, int size){
        double bigger = v[0];
        for(int i=0;i<size;i++){
            bigger = v[0];
        }
        System.out.println("** OPERATION BIGGER RESULT : "+bigger);
        return bigger;
    }

    private double averageValue(double[] v, int size){
        double total = 0;
        for(int i=0;i<size;i++){
            total = total + v[i];
        }
        System.out.println("** OPERATION AVERAGE RESULT : "+(total/size));
        return total/size;
    }

    public void run(){
        if(this.operation == OPERATION_AVERAGE){
            average = averageValue(values,size);
        }else if(this.operation == OPERATION_BIGGER){
            bigger = biggerValue(values,size);
        }else if(this.operation == OPERATION_SMALLER){
            smaller = smallerValue(values,size);
        }
    }
}
