import java.util.concurrent.Semaphore;

public class ProducerConsumerQueueSemaphore {
    int in, out, size;
    long buff[];
    Semaphore nonEmpty, nonFull, manipulation;

    ProducerConsumerQueueSemaphore(int s) {
        size = s;
        buff = new long[size];
        in = out = 0;
        nonEmpty = new Semaphore(0);
        nonFull = new Semaphore(size);
        manipulation = new Semaphore(1);

    }
    private int next(int i) {
        return (i+1) % size;
    }

    void enqueue(long x) {
        try{
            nonFull.acquire();
            manipulation.acquire();
            buff[in] = x;
            in = next(in);
        }
        catch (InterruptedException ex) {}
        finally {
            manipulation.release();
            nonEmpty.release();
        }
    }
    long dequeue() {
        long x = 0;
        try {
            nonEmpty.acquire();
            manipulation.acquire();
            x = buff[out];
            out = next(out);

        }
        catch (InterruptedException ex) {}
        finally {
            manipulation.release();
            nonFull.release();
        }
        return x;
    }
}
