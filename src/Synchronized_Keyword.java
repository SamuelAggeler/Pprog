public class Synchronized_Keyword {
    int value;
    private long c1 = 0, c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    // public synchronized void inc(long delta) {
    // this.value += delta;
    // }

    public void inc(long delta) {
        synchronized (this) {
            this.value += delta;
        }
    }

    public void inc1() {
        synchronized (lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized (lock2) {
            c2++;
        }
    }

    public void foo() {
        int pp;
        synchronized (this) {
            pp = 1;
        }
        System.out.println(pp);
    }

}
