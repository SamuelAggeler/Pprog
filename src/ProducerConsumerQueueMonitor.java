public class ProducerConsumerQueueMonitor {
    int in, out, size;
    long[] buff;

    ProducerConsumerQueueMonitor(int s) {
        size = s;
        buff = new long[size];
        in = out = 0;
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

    public boolean isFull() {
        return (in+1) % size == out;
    }

    public boolean isEmpty() {
        return in == out;
    }

    private int next(int i) {
        return (i+1) % size;
    }
    synchronized void enqueue(long x) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        doenqueue(x);
        notifyAll();
    }

    synchronized long dequeue() {
        long x;
        while (isEmpty()) {
            try {
                wait();
            } catch ( InterruptedException e) {}
        }
        x = dodequeue();
        notifyAll();
        return x;
    }
}
