import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(
                in.readLine().split(" ")
            ).mapToInt(
                x -> Integer.parseInt(x)
            ).toArray();
        int n = tmp[0], k = tmp[1];

        int[] points = Arrays.stream(
                in.readLine().split(" ")
            ).mapToInt(
                x -> Integer.parseInt(x)

            ).toArray();

        System.out.println(kthRandomPartialReverseSort(points, 0, n-1, k));
    }

    static int kthRandomPartialReverseSort(int[] nums, int b, int e, int k) {
        if (b > e) {
            return -1; // error
        }

        Random ran = new Random();
        int p = ran.nextInt(e - b + 1) + b;
        swap(nums, e, p);

        int pivotVal = nums[e];
        int i = b - 1;
        for (int j = b; j < e; j++) {
            if (nums[j] > pivotVal) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, e);

        if (k-1 < i) {
            return kthRandomPartialReverseSort(nums, b, i-1, k);
        } else if (i < k-1) {
            return kthRandomPartialReverseSort(nums, i+1, e, k);
        } else { // i == k-1
            return nums[k-1];
        }
    }

    static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
