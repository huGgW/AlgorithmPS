import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // for convenience of algorithm add different, unrelated character to string.
    String a = "_" + sc.next();
    String b = "!" + sc.next();

    int m = a.length(), n = b.length();
    int[][] LCS = new int[m][n];

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (a.charAt(i) == b.charAt(j)) {
          LCS[i][j] = 1 + LCS[i-1][j-1];
        }
        else {
          LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
        }
      }
    }

    System.out.println(LCS[m-1][n-1]);
  }
}