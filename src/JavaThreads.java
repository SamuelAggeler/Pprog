
import java.util.*;

public class JavaThreads implements Runnable{

    
    @Override
    public void run() {
        System.out.println("t");
    }
    public static void main(String[] args){
        Thread t = new Thread("name");
        t.start();
        t.run();

    }

  
}
