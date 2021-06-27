import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingCounter {
    private AtomicInteger value;

    public int getVal() {
        return value.get();
    }

    public int inc() {
        int v;
        do {
            v = value.get();
        } while (!value.compareAndSet(v, v+1));
    
    return v+1;
    }
}
