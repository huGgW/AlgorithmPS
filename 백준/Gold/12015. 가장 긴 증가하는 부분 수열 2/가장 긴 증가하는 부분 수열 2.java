import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                System.in
            )
        );

        int n = Integer.parseInt(reader.readLine().trim());
        int[] nums = Arrays.stream(
            reader.readLine().trim().split(" ")
        ).mapToInt(
            (s) -> { return Integer.parseInt(s); }
        ).toArray();
        reader.close();

        ArrayList<Integer> list = new ArrayList<>(n);
        for (int v : nums) {
            insertVal(list, v);
        }

        System.out.println(list.size());
    }

    static void insertVal(ArrayList<Integer> list, int val) {
        int b = 0;
        int e = list.size() - 1;

        int p = binarySearch(list, val, b, e);
        if (p == list.size()) {
            list.add(val);
        } else {
            list.set(p, val);
        }
    }

    static int binarySearch(ArrayList<Integer> list, int val, int b, int e) {
        if (b > e) {
            return b;
        }

        int m = (b + e) / 2;
        int pivot = list.get(m);

        if (val < pivot) {
            return binarySearch(list, val, b, m-1);
        } else if (val > pivot) {
            return binarySearch(list, val, m+1, e);
        } else {
            return m;
        }
    }
}