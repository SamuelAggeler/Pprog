import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingQueue {
    AtomicReference<QueueNodeAtomic> head = new AtomicReference<>();
    AtomicReference<QueueNodeAtomic> tail = new AtomicReference<>();

    public NonBlockingQueue() {
        QueueNodeAtomic node = new QueueNodeAtomic(null);
        head.set(node); tail.set(node);
    }

    public void enqueue(T item) {
        QueueNodeAtomic node = new QueueNodeAtomic(item);
        while (true) {
            QueueNodeAtomic last = tail.get();
            QueueNodeAtomic next = last.next.get();
            if (next == null) {
                if (last.next.compareAndSet(null, node)) {
                    tail.compareAndSet(last, node);
                    return;
                }
            } else {
                tail.compareAndSet(last, next);
            }
        }
    }

    public T dequeue() {
        while (true) {
            QueueNodeAtomic first = head.get();
            QueueNodeAtomic last = tail.get();
            QueueNodeAtomic next = first.next.get();
            if (first == last) {
                if (next == null) {
                    return null;
                }else {
                    tail.compareAndSet(last, next);
                }
            } else {
                T value = next.item;
                if (head.compareAndSet(first, next)) {
                    return value;
                }
            }
        }
    }
}
