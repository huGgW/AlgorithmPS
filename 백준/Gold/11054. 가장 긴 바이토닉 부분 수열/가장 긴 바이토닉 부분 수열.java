import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    int[] nums = Arrays.stream(
      in.readLine().split(" ")
    ).mapToInt(x -> Integer.parseInt(x))
    .toArray();

    int[] decreasingReverse = new int[n];
    int[] decreaseNormal = new int[n];

    // fill decreasingFrom
    decreasingReverse[0] = 1;
    for (int i = 1; i < n; i++) {
      int cnt = 0;
      for (int j = i-1; j >= 0; j--) {
        if (nums[j] < nums[i] && decreasingReverse[j] > cnt) {
          cnt = decreasingReverse[j];
        }
      }
      decreasingReverse[i] = ++cnt;
    }

    // fill increasingFrom
    decreaseNormal[n-1] = 1;
    for (int i = n-2; i >= 0; i--) {
      int cnt = 0;
      for (int j = i+1; j < n; j++) {
        if (nums[j] < nums[i] && decreaseNormal[j] > cnt) {
          cnt = decreaseNormal[j];
        }
      }
      decreaseNormal[i] = ++cnt;
    }

    // find maximum
    int max = 0;
    for (int i = 0; i < n; i++) {
      int tmp = decreaseNormal[i] + decreasingReverse[i];
      max = (tmp > max) ? tmp : max;
    }

    System.out.println(--max);

  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}