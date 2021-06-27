import java.util.concurrent.atomic.*;

public class FilterLock {
    AtomicIntegerArray level;
    AtomicIntegerArray victim;
    volatile int n;

    FilterLock(int n) {
        this.n = n;
        level = new AtomicIntegerArray(n);
        victim = new AtomicIntegerArray(n);
    }
    
    boolean Others(int me, int lev) {
        for(int k = 0; k < n; k++) {
            if(k != me && level.get(k) >= lev) {
                return true;
            }
        }
        return false;
    }
    public void Acquire(int me) {
        for(int lev = 1; lev < n; lev++) {
            level.set(me, lev);
            victim.set(lev, me);
            while(me == victim.get(lev) && Others(me, lev));
        }
    }
    public void Release(int me) {
        level.set(me, 0);
    }
}
