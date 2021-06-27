import java.util.concurrent.atomic.AtomicIntegerArray;

public class BakeryLock {
    AtomicIntegerArray flag;
    AtomicIntegerArray label;
    final int n;

    BakeryLock(int n) {
        this.n = n;
        flag = new AtomicIntegerArray(n);
        label = new AtomicIntegerArray(n);
    }
    /*

    find the maximum label in the label array and return it

    */

    int MaxLabel() {
        int max = label.get(0);
        for (int i = 1; i < n; i++) {
            max = Math.max(max, label.get(i));
        }
        return max;
    }

    /*

    checks if someone has raised his flag and a higher label then me and if so returns true
    else returns false

    */

    boolean Conflict(int me) {
        for (int i = 0; i < n; i++) {
            if (i != me && flag.get(i) != 0) {
                int diff = label.get(i) - label.get(me);
                if (diff < 0 || diff == 0 && i < me) {
                    return true;
                }
            }
        }
        return false;
    }

    /*

    raises flag and sets label to the max + 1(last person in the queue now)

    */

    public void Acquire(int me) {
        flag.set(me, 1);
        label.set(me, MaxLabel() + 1);
        while(Conflict(me));
    }

    /*

    once done lower my flag(set it to 0)

    */

    public void Release(int me) {
        flag.set(me, 0);
    }
}