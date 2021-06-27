import java.util.Random;

public class Backoff {
    long maxDelay;
    int limit;

    public Backoff(long maxDelay, int limit){
        this.maxDelay = maxDelay;
        this.limit = limit;
    }
    
    public void backoff() throws InterruptedException {
        Random rand = new Random();
        int delay = rand.nextInt(limit);
        if (limit < maxDelay) {
            limit = 2*limit;
        }
        Thread.sleep(delay);
    }
}
