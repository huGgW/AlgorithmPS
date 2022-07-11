import java.util.Scanner;
public class Main {
  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int k = sc.nextInt();
    sc.close();

    System.out.println(combRemainder(n, k, 10007));
  }
  public static int combRemainder(int n, int k, int d) {
    int[][] combRemainderTable = fillCombRemainderTable(n, d);
    return combRemainderTable[n][k];
  }
  public static int[][] fillCombRemainderTable(int n, int d) {
    int[][] combRemainderTable = new int[n+1][n+1];

    // initialization
    for (int i = 1; i <= n; i++) {
      combRemainderTable[i][0] = 1;
    }
    combRemainderTable[1][1] = 1;

    // fill up using C(n, k) + C(n, k+1) = C(n+1, k+1) (Pascal's law)
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        combRemainderTable[i][j] = (combRemainderTable[i-1][j-1] + combRemainderTable[i-1][j]) % d;
      }
    }

    return combRemainderTable;
  }
}