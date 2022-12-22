import java.io.*;
import java.util.Arrays;

public class Main {
    static long counter = 0;
    static long dst = -1;
    static long result = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    System.in
                    )
                );

        String[] tmp = in.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        dst = Long.parseLong(tmp[1]);

        long[] A = Arrays.stream(in.readLine().split(" "))
            .mapToLong((s) -> Long.parseLong(s))
            .toArray();

        mergeSort(A, 0, N-1);
        System.out.println(result);
    }

    static void mergeSort(long[] A, int p, int r) {
        if ((p < r)) {
            int q = (p + r) / 2;
            if (result == -1) {
                mergeSort(A, p, q);
            } else {
                return;
            }
            if (result == -1) {
                mergeSort(A, q+1, r);
            } else {
                return;
            }
            if (result == -1) {
                merge(A, p, q, r);
            } else {
                return;
            }
        }
    }
    static void merge(long[] A, int p, int q, int r) {
        long[] tmp = new long[r - p + 1];
        int i = p;
        int j = q+1;
        int t = 0;
        while ((i <= q && j <= r) && counter < dst) {
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++];
            } else {
                tmp [t++] = A[j++];
            }
            counter++;
        }
        if (counter == dst) {
            result = tmp[t-1];
            return;
        } else {
            while (i <= q) {
                tmp[t++] = A[i++];
                counter++;
                if (counter == dst) {
                    result = tmp[t-1];
                    return;
                }
            }
            while (j <= r) {
                tmp [t++] = A[j++];
                counter++;
                if (counter == dst) {
                    result = tmp[t-1];
                    return;
                }
            }
        }
        for (int k = p; k <= r; k++) {
            A[k] = tmp[k-p];
        }
        return;
    }
}