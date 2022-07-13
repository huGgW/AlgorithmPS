import java.io.*;
import java.util.*;

public class Main {

  static int n = 21;
  static int[][][] table = new int[n][n][n];

  public static void main(String[] args) {
  try {
    fillTable();

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    while (true) {
      int[] nums = Arrays.stream(in.readLine().split(" "))
        .mapToInt(x -> Integer.parseInt(x))
        .toArray();
      
      if (nums[0] == -1 && nums[1] == -1 && nums[2] == -1) { break; }

      int wResult = w(nums[0], nums[1], nums[2]);
      out.write(String.format("w(%d, %d, %d) = %d\n", nums[0], nums[1], nums[2], wResult));
    }
    out.flush();
    out.close();
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
  
  static void fillTable() {
    for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
      table[0][i][j] = 1;
      table[i][0][j] = 1;
      table[i][j][0] = 1;
    }

    for (int i = 1; i < n; i++) for (int j = 1; j < n; j++) for (int k = 1; k < n; k++) {
      if (i < j && j < k) {
        table[i][j][k] = table[i][j][k-1] + table[i][j-1][k-1] - table[i][j-1][k];
      }
      else {
        table[i][j][k] = table[i-1][j][k] + table[i-1][j-1][k] + table[i-1][j][k-1] - table[i-1][j-1][k-1];
      }
    }
  }

  public static int w(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0) {
      return 1;
    }
    else if (a >= n || b >= n || c >= n) {
      return table[n-1][n-1][n-1];
    }
    else {
      return table[a][b][c];
    }
  }
}