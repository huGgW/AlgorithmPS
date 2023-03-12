import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static final int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        // Read
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

        // Initialize Map, Stack, Result Arr
        Deque<Integer> indexStack = new LinkedList<>();
        int[] results = new int[n];
        int[] counts = new int[MAX];
        for (int x : nums) {
            counts[x-1]++;
        }

        // Traverse
        for (int i = 0; i < n; i++) {
            int cntOfIdx = counts[nums[i] - 1];

            while (!indexStack.isEmpty()) {
                int cntOfStacked = counts[nums[indexStack.peek()] - 1];
                if (cntOfIdx > cntOfStacked) {
                    int popIndex = indexStack.pop();
                    results[popIndex] = nums[i];
                } else {
                    break;
                }
            }

            indexStack.push(i);
        }

        // Write
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                System.out
            )
        );

        for (int cnt : results) {
            writer.write(String.valueOf((cnt != 0) ? cnt : -1));
            writer.write(" ");
        }
        writer.flush();
        writer.close();
    }
}