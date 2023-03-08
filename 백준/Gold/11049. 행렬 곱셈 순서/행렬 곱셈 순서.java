import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    System.in
                    )
                );

        // Read inputs
        int N = Integer.parseInt(reader.readLine().trim());
        Pair[] matrices = new Pair[N];

        for (int i = 0; i < N; i++) {
            matrices[i] = new Pair(
                    reader.readLine().trim().split(" ")
                    );
        }
        reader.close();

        // Dynamic programming
        long[][] lookup = new long[N][N];
        long result = minCostMatMult(lookup, matrices, 0, N-1);

        // Print result
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    System.out
                    )
                );

        writer.write(String.format("%d\n", result));
        writer.close();
    }

    // Assumes always b >= e
    static long minCostMatMult(long[][] lookup, Pair[] matrices, int b, int e) {
        if (b == e) { return 0; }
        if (lookup[b][e] != 0) { return lookup[b][e]; }

        long minCost = -1;
        for (int m = b; m < e; m++) {
            long leftCost = minCostMatMult(lookup, matrices, b, m);
            long rightCost = minCostMatMult(lookup, matrices, m+1, e);
            long tmpCost = leftCost + rightCost 
                + ((long)matrices[b].first * matrices[m].second * matrices[e].second);
            if (minCost > tmpCost || minCost == -1) {
                minCost = tmpCost;
            }
        }

        lookup[b][e] = minCost;
        return lookup[b][e];
    }
}

class Pair {
    public int first;
    public int second;

    Pair(String[] arr) {
        first = Integer.parseInt(arr[0]);
        second = Integer.parseInt(arr[1]);
    }
}