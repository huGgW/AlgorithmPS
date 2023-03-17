import java.io.*;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    System.in
                )
            );

        String[] tmp = reader.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int k = Integer.parseInt(tmp[1]);

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(reader.readLine());
        }

        long[] table = new long[k+1];

        // Fill table for smallest coin
        for (int i = 0; i <= k; i++) {
            table[i] = (i % coins[0] == 0) ? 1 : 0;
        }

        // Fill table for next smallest coin -> biggest coin
        for (int pos = 1; pos < n; pos++) {
            for (int val = k; val >= 0; val--) {
                long cnt = 0;
                for (int v = val; v >= 0; v-=coins[pos]) {
                    cnt += table[v];
                }
                table[val] = cnt;
            }
        }

        System.out.println(table[k]);
    }
}