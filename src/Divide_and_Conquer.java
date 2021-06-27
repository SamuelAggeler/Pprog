public class Divide_and_Conquer extends Thread {
    int array[];
    int h, l;
    int result;
    int SEQ_CUTOFF;

    public Divide_and_Conquer(int array[], int l, int h, int SEQ_CUTOFF) {
        super();
        this.array = array;
        this.h = h;
        this.l = l;
        this.SEQ_CUTOFF = SEQ_CUTOFF;
        
    }
    @Override
    public void run() {
        int size = h -l;
        if (size < SEQ_CUTOFF) {
            for(int i = l; i < h; i++) {
                result += array[i];
            }
        } else {
            int mid = size / 2;
            Divide_and_Conquer t1 = new Divide_and_Conquer(array, l, l + mid, SEQ_CUTOFF);
            Divide_and_Conquer t2 = new Divide_and_Conquer(array, l + mid , h, SEQ_CUTOFF);
            t1.start();
            t2.run();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = t1.result + t2.result;
        }
    }

}
