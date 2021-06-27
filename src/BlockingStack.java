public class BlockingStack {
    StackNode top = null;

    synchronized public void push(Long item) {
        top = new StackNode(item, top);
    }

    synchronized public long pop() {
        if (top == null) {
            return null;
        }
        Long item = top.item;
        top = top.next;
        return item;
    }
}
