import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        int k = tmp[0]; 
        int n = tmp[1];

        long[] wires = new long[k];
        // int minWire = 0;
        long maxWire = 0;
        for (int i = 0; i < k; i++) {
            wires[i] = Long.parseLong(in.readLine());
            maxWire = (maxWire < wires[i]) ? wires[i] : maxWire;
            // if (minWire == 0 || wires[i] < minWire) {
            //     minWire = wires[i];
            // }
        }

        long min = 1;
        long max = maxWire+1;

        while (max - min > 1) {
            long mid = (min + max) / 2;
            long cnt = cntWires(mid, wires);
            if (cnt >= n) {
                min = mid;
            } else if (cnt < n) {
                max = mid;
            }
        }

        System.out.println(min);
    }

    static long cntWires(long len, long[] wires) {
        long cnt = 0;
        for (long w : wires) {
            cnt += w / len;
        }
        return cnt;
    }
}