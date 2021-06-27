public class Fork_Join extends java.lang.Thread {

    int lo;
    int hi;
    int[] input;
    int ans=0;


    Fork_Join(int[] a, int l, int h) {
        lo = l;
        hi = h;
        input = a;
    }


    public void run() {
        for(int i=lo; i < hi; i++) {
            ans += input[i];
        }
    }

    public static int sum(int[] input) {
        int sum = 0;
        for(int i=0; i < input.length; i++) {
            sum += input[i];
        }
        return sum;
    }

    int sumthread(int[] input) {
        int len = input.length;
        int ans = 0;
        Fork_Join[] ts = new Fork_Join[4];
        for(int i=0; i < 4; i++) {
            ts[i] = new Fork_Join(input, i*len/4, (i+1)*len/4);
            ts[i].start();
        }
        for(int i=0; i < 4; i++) {
            try {
                ts[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ans += ts[i].ans;
        }
       return ans;
    }

    int sumthreadparamter(int[] input, int numTS) {
        int len = input.length;
        int ans = 0;
        Fork_Join[] ts = new Fork_Join[numTS];
        for(int i=0; i < numTS; i++) {
            ts[i] = new Fork_Join(input, i*len/numTS, (i+1)*len/numTS);
            ts[i].start();
        }
        for(int i=0; i < numTS; i++) {
            try {
                ts[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ans += ts[i].ans;
        }
       return ans;
    }

}