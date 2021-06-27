public class Sequential_PrefixSum {
    

    int[] prefix_sum(int[] input) {
        int[] output = new int[input.length];
        output[0] = input[0];
        for (int i=1; i < input.length; i++) {
            output[i] = output[i-1]+output[i];
        }
        return output;
    }
}
