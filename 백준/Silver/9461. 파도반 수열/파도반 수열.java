/*
 * f: 1, 1, 1, 2, 2
 * f(n) = f(n-1) + f(n-5)
 */

import java.io.*;

public class Main {
  public static void main (String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    int t = Integer.parseInt(in.readLine());

    for (int i = 0; i < t; i++) {
      int n = Integer.parseInt(in.readLine());
      out.write(Long.toString(halfWave(n)));
      out.newLine();
    }

    in.close();
    out.flush();
    out.close();
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }

  public static long halfWave(int n) {
    long[] hwTable = new long[(n > 5) ? n : 5];
    hwTable[0] = 1; hwTable[1] = 1; hwTable[2] = 1; hwTable[3] = 2; hwTable[4] = 2; 

    for (int i = 5; i < n; i++) {
      hwTable[i] = hwTable[i-1] + hwTable[i-5];
    }

    return hwTable[n-1];
  }
}