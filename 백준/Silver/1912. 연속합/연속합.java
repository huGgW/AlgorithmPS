import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    int[] nums = Arrays.stream(in.readLine().split(" "))
      .mapToInt(x -> Integer.parseInt(x)).toArray();


    // Find maximum sums
    int sum = -1;
    int max = nums[0];

    for (int x: nums) {
      if (sum < 0) {
        sum = x;
      }
      else {
        sum += x;
      }
      max = (sum > max) ? sum : max;
    }

    // print maximum sum
    System.out.println(max);
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}