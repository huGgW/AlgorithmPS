import java.io.*;
import java.util.*;

public class Main { 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(in.readLine());
        int[] arr = new int[num];

        // Get the input
        for (int i = 0; i < num; i++) {
            int n = Integer.parseInt(in.readLine());
            arr[i] = n;
        }
        in.close();

        // Make the array of difference between two elements
        // Difference will be always the multiple of M
        int[] diff = new int[(int)Math.ceil((double)num / 2.0)];
        if (num % 2 == 0) {
            for (int i = 0; i < num/2; i++) {
                diff[i] = Math.abs(arr[2*i] - arr[2*i + 1]);
            }
        } else {
            for (int i = 0; i < num/2; i++) {
                diff[i] = Math.abs(arr[2*i] - arr[2*i + 1]);
            }
            diff[num/2] = Math.abs(arr[num-2] - arr[num-1]);
        }

        // Find the M
        int M = getGCDfromGroup(diff);

        // Print the divisor of M
        printDivisor(M);
    }
    public static int getGCD(int a, int b) {
        int bigger = (a > b) ? a : b;
        int smaller = (a + b) - bigger;

        if (smaller == 0) {
            return bigger;
        }
        else {
            return getGCD(smaller, bigger % smaller);
        }
    }
    public static int getGCDfromGroup(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] newNums;
        if (n % 2 == 0) {
            newNums = new int[n/2];
            for (int i = 0; i < n/2; i++) {
                newNums[i] = getGCD(nums[2*i], nums[2*i + 1]);
            }
        } else {
            newNums = new int[n/2 + 1];
            for (int i = 0; i < n/2; i++) {
                newNums[i] = getGCD(nums[2*i], nums[2*i + 1]);
            }
            newNums[n/2] = nums[n-1];
        }
        return getGCDfromGroup(newNums);
    }
    public static void printDivisor(int m) throws IOException {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // add divisor below sqrt to queue in order, over sqrt to stack in reversed order
        for (int n = 1; n <= Math.sqrt(m); n++) {
            if (m % n == 0) {
                int pair = m / n;
                queue.add(n);
                if (pair != n) { stack.push(pair); }
            }
        }

        // print out in order
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        queue.poll(); // remove 1
        while (!queue.isEmpty()) {
            out.write(Integer.toString(queue.poll()) + " ");
        }
        while (!stack.isEmpty()) {
            out.write(Integer.toString(stack.pop()) + " ");
        }
        
        out.flush();
        out.close();
    }
}