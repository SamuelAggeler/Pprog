public class Memory_Reordering {
    private int x = 0;
    private int y = 0;

    void f() {
        //thread 1 starts here
        x = 1;
        y = 1;
    }
    void g(){
        //thread 2 starts here
        int a = x;
        int b = y;
        assert( b >= a);

    }
}
