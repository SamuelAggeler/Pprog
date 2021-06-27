public class PetersonLock {

    volatile boolean flag[] = new boolean[2];
    volatile int victim;

    public void Acquire(int id) {
        flag[id] = true;
        victim = id;
        while(flag[1-id] && victim == id);
    }
    public void Release(int id) {
        flag[id] = false;
    }
}
