import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class FibonacciSeries_ForkJoin {
    public static void main(String[] arg) {
        int size = 25;
        Long startTime = Calendar.getInstance().getTimeInMillis();
        final ForkJoinPool pool = new ForkJoinPool();
        List fibonacciSeries = new ArrayList <>();
        for (int index = 0; index < size; index++) {
            FibonacciSeriesGeneratorTask task = new FibonacciSeriesGeneratorTask(
                    index);
            fibonacciSeries.add(pool.invoke(task));
        }
        Long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(endTime - startTime);
        dumpList(fibonacciSeries);
    }
    public static void dumpList(List list) {
        int index = 0;
        for (Object object: list) {
            System.out.printf("%d\t%d\n", index++, object);
        }
    }
}
class FibonacciSeriesGeneratorTask extends RecursiveTask {
    private static final long serialVersionUID = 1L;
    private Integer index = 0;
    public FibonacciSeriesGeneratorTask(Integer index) {
        super();
        this.index = index;
    }
    @Override
    protected Integer compute() {
        if (index == 0) {
            return 0;
        }
        if (index < 2) {
            return 1;
        }
        final FibonacciSeriesGeneratorTask worker1 = new FibonacciSeriesGeneratorTask(index - 1);
        worker1.fork();
        final FibonacciSeriesGeneratorTask worker2 = new FibonacciSeriesGeneratorTask(index - 2);
        return (worker1.compute()) + (int)(worker1.join());
    }
}
