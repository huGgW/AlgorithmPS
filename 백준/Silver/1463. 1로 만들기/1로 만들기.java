import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] cnt = new int[n+1]; // for convinience

    for (int i = 1; i < n; i++) {
      // count reverse for all cases
      int tmpCnt = cnt[i] + 1;
      // count divide by 3
      int times3 = i * 3;
      if (times3 <= n) {
        cnt[times3] = (tmpCnt < cnt[times3] || cnt[times3] == 0) ? tmpCnt : cnt[times3];
      }
      // count divide by 2
      int times2 = i * 2;
      if (times2 <= n) {
        cnt[times2] = (tmpCnt < cnt[times2] || cnt[times2] == 0) ? tmpCnt : cnt[times2];
      }
      // count sub by 1
      int sub1 = i + 1;
      if (sub1 <= n) {
        cnt[sub1] = (tmpCnt < cnt[sub1] || cnt[sub1] == 0) ? tmpCnt : cnt[sub1];
      }
    }

    System.out.println(cnt[n]);
  }
}