import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());
    int[] nums = Arrays.stream(
        in.readLine().split(" ")
      ).mapToInt(
        x -> Integer.parseInt(x)
      ).toArray();

    // i번째 수부터 시작했을때 0번째 수까지 연속으로 작아지는 최대 개수
    int[] maxCntGoDown = new int[n];
    maxCntGoDown[0] = 1;
    for (int i = 1; i < n; i++) {
      int cnt = 0;
      for (int j = i-1; j >= 0; j--) {
        if (nums[j] < nums[i] && cnt < maxCntGoDown[j]) {
          cnt = maxCntGoDown[j];
        }
      }
      maxCntGoDown[i] = ++cnt;
    }

    System.out.println(Arrays.stream(maxCntGoDown).max().getAsInt());
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }

}