import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(in.readLine());
            }
            in.close();

            int min = -4000;
            int max = 4000;
            arr = counting_sort(arr, min, max);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            // 산술평균
            out.write(Integer.toString(average(arr)));
            out.newLine();
            // 중앙값
            out.write(Integer.toString(arr[((n-1)/2)]));
            out.newLine();
            // 최빈값
            out.write(Integer.toString(most(arr)));
            out.newLine();
            // 범위
            out.write(Integer.toString(arr[n-1] - arr[0]));
            out.newLine();

            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int[] counting_sort(int[] arr, int min, int max) {
        int[] cnt = new int[max - min + 1];
        // cnt[x] = count of x
        for (int x : arr) {
            cnt[x+4000]++;
        }
        // cnt[x] = count of less or equal to x
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i-1];
        }
        // sort in backward
        int[] sorted = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            int x = arr[i];
            cnt[x+4000]--;
            sorted[cnt[x+4000]] = x;
        }
        return sorted;
    }
    static int average(int[] arr) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return Math.round((float)sum / arr.length);
    }
    static int most(int[] arr) {
        // Assume that range is [-4000,4000]
        int before = arr[0];
        int cnt = 0;
        int max_val = 4001;
        int max_cnt = 0;
        int dup_cnt = 1;

        for (int x : arr) {
            if (before != x) {
                if (cnt > max_cnt) {
                    max_val = before;
                    dup_cnt = 1;
                    max_cnt = cnt;
                }
                else if (cnt == max_cnt) {
                    dup_cnt++;
                    if (dup_cnt <= 2) {
                        max_val = before;
                    }
                }
                cnt = 1;
            }
            else {
                cnt++;
            }
            before = x;
        }
        if (cnt > max_cnt) {
            max_val = before;
            dup_cnt = 1;
            max_cnt = cnt;
        }
        else if (cnt == max_cnt) {
            dup_cnt++;
            if (dup_cnt <= 2) {
                max_val = before;
            }
        }
        return max_val;
    }
}