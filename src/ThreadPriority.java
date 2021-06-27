import java.lang.Thread.State;

import Codeexamples.Lecture3.Calculator;

public class ThreadPriority {
    public static void main(String[] args){
        Thread threads[] = new Thread[10];
        Thread.State status[] = new Thread.State[10];

        for(int i=0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if ((i%2) == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+i);
        }
        for(int i=0; i < 10; i++) {
            System.out.println(threads[i].getState());
            status[i] = threads[i].getState();
            threads[i].start();
        }

        boolean finish = false;             //bussy waiting
        while (!finish) {
            for (int i=0; i < 10; i++) {
                if (threads[i].getState() != status[i]){
                    status[i] = threads[i].getState();
                }
            }
            finish=true;
            for(int i=0; i <10; i++) {
                finish = finish && (threads[i].getState() == State.TERMINATED);
            }
        }


    }
}
