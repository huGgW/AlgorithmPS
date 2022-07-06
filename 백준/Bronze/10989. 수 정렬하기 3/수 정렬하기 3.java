import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];
            int max = 10000;

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(in.readLine());
            }
            in.close();

            int[] sorted = countingSort(arr, max);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int x : sorted) {
                out.write(Integer.toString(x) + "\n");
            }
            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] countingSort(int[] arr, int max) {
        int[] cnt = new int[max+1];
        // cnt[x] = count of x
        for (int x : arr) {
            cnt[x]++;
        }
        // cnt[x] = count of less or equal to x
        for (int i = 1; i <= max; i++) {
            cnt[i] += cnt[i-1];
        }
        // sort in backward
        int[] sorted = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            int x = arr[i];
            cnt[x]--;
            sorted[cnt[x]] = x;
        }
        return sorted;
    }
}