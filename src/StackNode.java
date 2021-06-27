public class StackNode {
    public final Long item;
    public StackNode next;

    public StackNode (Long item) {
        this.item = item;
    }

    public StackNode(Long item, StackNode n) {
        this.item = item;
        next = n;
    }
}
