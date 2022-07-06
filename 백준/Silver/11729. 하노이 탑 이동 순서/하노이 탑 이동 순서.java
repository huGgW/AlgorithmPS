import java.util.Scanner;
import java.util.ArrayDeque;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Initialize
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        // Stacks
        ArrayDeque<Integer> one = new ArrayDeque<>(n);
        ArrayDeque<Integer> two = new ArrayDeque<>(n);
        ArrayDeque<Integer> three = new ArrayDeque<>(n);
        for (int i = n; i >= 1; i--) {
            one.push(i);
        }
        ArrayDeque[] Stacks = new ArrayDeque[]{one, two, three};

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            out.write(Integer.toString(cntHanoi(n)) + "\n");
            hanoi(n, 0, 2, Stacks, out);
            out.flush();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    static int cntHanoi(int n) {
        if (n == 1) { return 1; }
        else { return (1 + (2 * cntHanoi(n-1))); }
    }
    static void hanoi(int x, int from, 
        int to, ArrayDeque[] Stacks, BufferedWriter out) 
        throws IOException {
        // Base case
        if (x == 1) {
            Stacks[to].push(Stacks[from].pop());
            out.write(Integer.toString(from+1) + " " + Integer.toString(to+1) + "\n");
        }
        else {
            // Find left base
            int left = (3 - (from + to));
            // Move x-1 Planes to left base
            hanoi(x-1, from, left, Stacks, out);
            // Move x Plane to the bottom of directed base
            Stacks[to].push(Stacks[from].pop());
            out.write(Integer.toString(from+1) + " " + Integer.toString(to+1) + "\n");
            // Move other x-1 planes back to directed base
            hanoi(x-1, left, to, Stacks, out);
        }
    }
}