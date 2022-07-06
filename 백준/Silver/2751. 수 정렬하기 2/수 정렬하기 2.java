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

            quicksort_random(arr, 0, n-1);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int x : arr) {
                out.write(Integer.toString(x) + "\n");
            }
            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void quicksort_random(int[] arr, int b, int e) {
        int len = e - b + 1;
        if (len <= 0) { return; }
        
        int piv_i = (int)Math.floor(Math.random() * len) + b;
        swap(arr, piv_i, e);
        int piv = arr[e];
        int i = b-1;
        for (int j = b; j < e; j++) {
            if (arr[j] < piv) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i+1, e);
        quicksort_random(arr, b, i);
        quicksort_random(arr, i+2, e);
    }
    static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}