public class ProducerConsumerQueueSleep {
    private int in;
    private int out;
    private int size;
    private long[] buffer;
    private int timeout;

    ProducerConsumerQueueSleep(int size, int timeout) {
        this.size = size;
        in = out = 0;
        buffer = new long[size];
        this.timeout = timeout;
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

    public synchronized void enqueue(long item) throws InterruptedException{
        while(true) {
            synchronized (this) {
                if( !isFull()) {
                    doenqueue(item);
                    return;
                }
            }
            Thread.sleep(timeout);
        }
    }

    public synchronized long dequeue() {
        while(isEmpty()){}
        return dodequeue();
    }
    
}
