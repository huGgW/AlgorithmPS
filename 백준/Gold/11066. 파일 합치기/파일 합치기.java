import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Reader && Writer 
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                System.in
            )
        );
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                System.out
            )
        );

        int T = Integer.parseInt(reader.readLine().trim());
        for (int t = 0; t < T; t++) {
            // Read files
            reader.readLine();
            int[] files = Arrays.stream(
                reader.readLine().trim().split(" ")
            ).mapToInt (
                (s) -> { return Integer.parseInt(s); }
            ).toArray();

            long result = minimumCost(files);
            writer.write(String.format("%d", result) + "\n");
        }

        writer.flush();
        reader.close();
        writer.close();
    }

    static long minimumCost(int[] files) {
        int K = files.length;
        int[] partialSum = new int[K+1]; // Sum(i ~ j) = partialSum(j+1) - partialSum(i)
        long[][] lookup = new long[K][K];

        partialSum[0] = 0;
        for (int i = 1; i <= K; i++) {
            partialSum[i] = files[i-1] + partialSum[i-1];
        }

        return minimumPartialCost(files, partialSum, lookup, 0, K-1);
    }

    static long minimumPartialCost(int[] files, int[] partialSum, long[][] lookup, int b, int e) {
        if (b == e || lookup[b][e] != 0) {
            return lookup[b][e];
        }

        if (e - b == 1) {
            return files[b] + files[e];
        }

        int K = files.length;
        long tmpMin = -1;
        for (int m = b; m < e; m++) {
            long tmp = (
                    minimumPartialCost(files, partialSum, lookup, b, m)
                    + calcPartialSum(partialSum, b, m)
                ) + (
                    minimumPartialCost(files, partialSum, lookup, m+1, e)
                    + calcPartialSum(partialSum, m+1, e)
                );
            if (tmpMin == -1 || tmpMin > tmp) {
                tmpMin = tmp;
            }
        }

        lookup[b][e] = tmpMin;
        return tmpMin;
    }

    static int calcPartialSum(int[] partialSum, int i, int j) {
        return partialSum[j+1] - partialSum[i];
    }
}
