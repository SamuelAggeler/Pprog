import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack {
    AtomicReference<StackNode> top = new AtomicReference<>();

    public Long pop() {
        StackNode head, next;

        do {
            head = top.get();
            if (head == null) {
                return null;
            }
            next = head.next;
        } while (!top.compareAndSet(head, next));
        return head.item;
    }

    public void push(Long item) {
        StackNode newi = new StackNode(item);
        StackNode head;

        do{
            head = top.get();
            newi.next = head;
        } while (!top.compareAndSet(head, newi));
    }
}
