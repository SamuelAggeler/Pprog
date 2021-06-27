public class BlockingQueue <T> {
    QueueNode<T> head, tail;

    public synchronized void Enqueue( T item) {
        QueueNode<T> node = new QueueNode<T>(item);
        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
    }

    public synchronized T Dequeue() {
        T item = null;
        QueueNode<T> node = head;
        if (node != null) {
            item = node.item;
            head = node.next;
            if (head == null) {
                tail = null;
            }
        }
        return item;
    }
}
