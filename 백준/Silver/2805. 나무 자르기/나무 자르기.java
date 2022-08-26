import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        int n = tmp[0], m = tmp[1];

        int[] trees = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        int maxHeight = Arrays.stream(trees).max().getAsInt();
        int minHeight = 0;
        while (maxHeight - minHeight > 1) {
            int midHeight = (maxHeight + minHeight) / 2;
            if (cutLength(midHeight, trees) >= m) {
                minHeight = midHeight;
            } else {
                maxHeight = midHeight;
            }
        }

        System.out.println(minHeight);
    }

    static long cutLength(int height, int[] trees) {
        long result = 0;
        for (int t : trees) {
            result += (height >= t) ? 0 : t - height;
        }
        return result;
    }
}