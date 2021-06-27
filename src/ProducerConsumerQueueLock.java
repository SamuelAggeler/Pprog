import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerQueueLock {
    int in = 0, out = 0, size;
    long[] buff;
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    ProducerConsumerQueueLock(int s) {
        size = s;
        buff = new long[size];

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

    void enqueue(long x) {

        lock.lock();
        while (isFull()) {
            try {
                notFull.await();
            } catch (InterruptedException e) {
            }
        }
        doenqueue(x);
        notEmpty.signal();
        lock.unlock();
    }

    long dequeue() {
        long x;
        lock.lock();
        while (isEmpty()) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
            }
        }
        x = dodequeue();
        notFull.signal();
        lock.unlock();
        return x;
    }
}