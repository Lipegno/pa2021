import java.util.ArrayList;

public class SPMDExecutor {

    private final double[][] mydata;
    private final int myoperation;

    public SPMDExecutor(double[][] mydata, int myoperation) {
        this.mydata = mydata;
        this.myoperation = myoperation;
    }

    private double[][] divideMatrix(double[][] matrix, int size, int sector_size, int sectors){
        int index = 0;
        int current_sector = 0;
        double[][] result = new double[sectors][sector_size];

        for(int i=0; i<size && index < 600;i++) {
            for (int j = 0; j<size && index < 600; j++) {
                result[current_sector][index] = matrix[i][j];
                index++;
                if(index==sector_size){
                    index=0;
                    current_sector++;
                }

            }

        }
        return result;
    }
    public void executeOperation(double[][] data, int operation){
        int num_threads = 6;
        int sector_size = (60 * 60) / num_threads;
        ArrayList<InstructionThread> threads = new ArrayList<>();
        double sectors[][] = divideMatrix(data, 60, sector_size,num_threads);
        for (int i = 0; i < num_threads; i++) {
            InstructionThread t = new InstructionThread(sectors[i], operation, sector_size);
            threads.add(t);
            t.start();
        }


        if(this.myoperation == InstructionThread.OPERATION_AVERAGE){
            double average = 0;
            for (int i = 0; i < num_threads; i++) {
                average = average + threads.get(i).getAverage();
            }
            System.out.println("** SIMD AVERAGE "+(average/num_threads)+" ***");
        }else if(this.myoperation == InstructionThread.OPERATION_BIGGER){
            double bigger = threads.get(0).getBigger();
            for (int i = 0; i < num_threads; i++) {
                if(threads.get(i).getBigger()>bigger)
                    bigger = threads.get(i).getBigger();
            }
            System.out.println("** SIMD BIGGER "+bigger+" ***");

        }else if(this.myoperation == InstructionThread.OPERATION_SMALLER){
            double smaller = threads.get(0).getSmaller();
            for (int i = 0; i < num_threads; i++) {
                if(threads.get(i).getSmaller()<smaller)
                    smaller = threads.get(i).getBigger();
            }
            System.out.println("** SIMD SMALLER "+smaller+" ***");
        }

    }

    public void run(){

        executeOperation(this.mydata, this.myoperation);
    }
}
