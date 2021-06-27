public class Synchronized_Assembly {
    
    public void foo() {
        int pp;
        synchronized (this) {
            pp = 1;
        }
    }
}
