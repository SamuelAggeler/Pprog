import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerQueueBarber {
    int in=0, out=0, size;
    long[] buff;
    final Lock lock = new ReentrantLock();
    int n = 0; final Condition notFull = lock.newCondition();
    int m; final Condition notEmpty = lock.newCondition();

    ProducerConsumerQueueBarber(int s) {
        size = s; m = size -1;
        buff = new long[size];
    }

    void enqueue(long x) {
        lock.lock();
        m--; if ( m < 0) {
            while (isFull()) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {}
            }
        }
        doenqueue(x);
        n++;
        if (n <= 0) {
            notEmpty.signal();
        }
        lock.unlock();
    }

    long dequeue() {
        long x;
        lock.lock();
        n--; if (n < 0) {
            while (isEmpty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {}
            }
        }
        x = dodequeue();
        m++;
        if (m <= 0) {
            notFull.signal();
        }
        lock.unlock();
        return x;
    }

    private int next(int i) {
        return (i + 1) % size;
    }

    public boolean isFull() {
        return (in + 1) % size == out;
    }

    public boolean isEmpty() {
        return in == out;
    }

    public synchronized void doenqueue(long item) {
        buff[in] = item;
        in = next(in);
    }

    public synchronized long dodequeue() {
        long item = buff[out];
        out = next(out);
        return item;
    }

    
}
