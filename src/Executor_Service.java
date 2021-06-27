import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor_Service implements Runnable{
    String mgs;

    public Executor_Service(String mgs){
        this.mgs = mgs;
    }

    public void run() {
        long id = Thread.currentThread().getId();
        System.out.println(mgs + " from Thread: " + id);
    }

    int ntasks = 1000;
    ExecutorService exs = Executors.newFixedThreadPool(4); {

    for(int i=0; i < ntasks; i++) {
        Executor_Service t = new Executor_Service("Hello from task" + i);
        exs.submit(t);
    }
    exs.shutdown();

    
}}
