import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[] arr = new int[n];
        String[] sarr = in.readLine().split(" ");
        in.close();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(sarr[i]);
        }

        // Make a sorted array
        int[] sorted = arr.clone();
        // quickSortRandom(sorted, 0, n-1);
        mergeSort(sorted, 0, n-1);

        // Record a rank to a rank HashMap
        HashMap<Integer, Integer> rank = new HashMap<>();
        int x = 0;
        for (int m : sorted) {
            if (!rank.containsKey(m)) {
                rank.put(m, x++);
            }
        }

        // Change arr to a rank
        for (int i = 0; i < n; i++) {
            arr[i] = rank.get(arr[i]);
        }

        // Print
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter((System.out)));
        for (int m : arr) {
            out.write(Integer.toString(m) + " ");
        }
        out.flush();
        out.close();
    }
    static void quickSortRandom(int[] arr, int b, int e) {
        int len = e - b + 1;
        if (len <= 1) {
            return;
        }

        int piv_idx = (int)Math.floor((Math.random() * len)) + b;
        swap(arr, piv_idx, e);
        int piv = arr[e];

        int i = b - 1;
        for (int j = b; j < e; j++) {
            if (arr[j] < piv) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, e);

        quickSortRandom(arr, b, i-1);
        quickSortRandom(arr, i+1, e);
    }
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void mergeSort(int[] arr, int b, int e) {
        if (e <= b) return;
        
        int mid = (b + e) / 2;

        mergeSort(arr, b, mid);
        mergeSort(arr, mid+1, e);

        merge(arr, b, mid, e);
    }
    static void merge(int[] arr, int b, int mid, int e) {
        int[] tmp = new int[e-b+1];

        int i = b;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= e) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            }
            else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= e) {
            tmp[t++] = arr[j++];
        }

        for (t = 0; t < tmp.length; t++) {
            arr[b+t] = tmp[t];
        }
    }
}