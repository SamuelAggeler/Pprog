public class ProducerConsumerQueue {
    private int in;
    private int out;
    private int size;
    private long[] buffer;

    ProducerConsumerQueue(int size) {
        this.size = size;
        in = out = 0;
        buffer = new long[size];
    }

    private int next(int i) {
        return (i+1) % size;
    }
    public synchronized void doenqueue(long item) {
        buffer[in] = item;
        in = next(in);
    }
    public synchronized long dodequeue() {
        long item = buffer[out];
        out = next(out);
        return item;
    }

    public boolean isFull() {
        return (in+1) % size == out;
    }

    public boolean isEmpty() {
        return in == out;
    }

    public synchronized void enqueue(long item) {
        while(isFull()){}
        doenqueue(item);
    }

    public synchronized long dequeue() {
        while(isEmpty()){}
        return dodequeue();
    }
}
