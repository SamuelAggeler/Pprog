import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fork_Join_FrameWork extends RecursiveTask<Long>{
    int low;
    int high;
    int[] array;

    Fork_Join_FrameWork(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    static ForkJoinPool fjPool = new ForkJoinPool();

    static long sumArray(int[] array) {
        return fjPool.invoke(new Fork_Join_FrameWork(array, 0, array.length));
    }

    protected Long compute() {
        if(high - low <= 1) {
            return (long) array[high];
        } else {
            int mid = low + (high - low) / 2;
            Fork_Join_FrameWork left = new Fork_Join_FrameWork(array, low, high);
            Fork_Join_FrameWork right = new Fork_Join_FrameWork(array, mid, high);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }

}
