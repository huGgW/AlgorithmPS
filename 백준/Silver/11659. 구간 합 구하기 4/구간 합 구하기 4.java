import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int[] tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
    int n = tmp[0];
    int m = tmp[1];

    int[] sums = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
    for (int i = 1; i < n; i++) {
      sums[i] += sums[i-1];
    }

    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < m; i++) {
      tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
      int s = tmp[0] - 1;
      int e = tmp[1] - 1;
      int sum;
      if (s >= 1) { sum = sums[e] - sums[s-1]; }
      else { sum = sums[e]; }
      out.write(Integer.toString(sum)); out.newLine();
    }

    out.flush();
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}