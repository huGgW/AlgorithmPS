import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int[] tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
    int n = tmp[0];
    int k = tmp[1];

    int[] nums = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
    int[] sums = new int[n-k+1];
    for (int i = 0; i < k; i++) {
      sums[0] += nums[i];
    }
    for (int i = 1; i < sums.length; i++) {
      sums[i] = sums[i-1] - nums[i-1] + nums[i+k-1];
    }

    System.out.print(Arrays.stream(sums).max().getAsInt());
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}