import java.io.*;

public class Main {
  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int num = Integer.parseInt(in.readLine());
      int[] cases = new int[2*num];

      int max = 0;
      for (int i = 0; i < num; i++) {
        String[] tmp = in.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        cases[2*i] = n;
        cases[2*i + 1] = m;
        if (m > max) max = m;
      }
      in.close();

      int[][] combTable = initializeCombTable(max);

      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
      for (int i = 0; i < num; i++) {
        out.write(Integer.toString(combTable[cases[2*i + 1]][cases[2*i]]));
        out.newLine();
      }
      out.flush();
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static int[][] initializeCombTable(int n) {
    int[][] combTable = new int[n+1][n+1];

    // Initialize
    for (int i = 0; i <= n; i++) {
      combTable[i][0] = 1;
    }
    combTable[1][1] = 1;

    // Fill table using C(n k) + C(n k+1) = C(n+1 k+1)
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        combTable[i][j] = combTable[i-1][j-1] + combTable[i-1][j];
      }
    }

    return combTable;
  }
}