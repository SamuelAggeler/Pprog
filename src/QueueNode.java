public class QueueNode<T> {
    public T item;
    public QueueNode<T> next;

    public QueueNode(T item) {
        this.item = item;
        next = null;
    }
}
