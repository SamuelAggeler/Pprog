public class RWLock_Monitor {
    int writers = 0;
    int readers = 0;

    synchronized void acquire_read() {
        while (writers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        readers++;
    }

    synchronized void release_read() {
        readers--;
        notifyAll();
    }

    synchronized void acquire_write() {
        while (writers > 0 || readers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        writers++;
    }

    synchronized void release_write() {
        writers--;
        notifyAll();
    }
}
