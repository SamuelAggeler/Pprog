import java.util.concurrent.atomic.AtomicReference;

public class QueueNodeAtomic <T> {
    public T item;
    public AtomicReference<QueueNodeAtomic> next;

    public QueueNodeAtomic(T item) {
        next = new AtomicReference<>(null);
        this.item = item;
    }

    public void SetItem(T item) {
        this.item = item;
    }
}
