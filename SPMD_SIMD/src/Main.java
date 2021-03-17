import java.util.ArrayList;

public class Main {



    public static void main(String args[]){
        System.out.println("** Main started **");
        double[][] matrix = new double[60][60];
        for(int i=0;i<60;i++){
            for(int j=0;j<60;j++){
                matrix[i][j] = Math.random()*100;
            }
        }

       /* try {
            SIMDExecutor averageExecutor = new SIMDExecutor(matrix,InstructionThread.OPERATION_AVERAGE);
            averageExecutor.start();
            averageExecutor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new SIMDExecutor(matrix,InstructionThread.OPERATION_BIGGER).start();*/
        new SIMDExecutor(matrix,InstructionThread.OPERATION_SMALLER).start();
        //SPMDExecutor sumExecutor = new SPMDExecutor(matrix,InstructionThread.OPERATION_AVERAGE);
    }
}
