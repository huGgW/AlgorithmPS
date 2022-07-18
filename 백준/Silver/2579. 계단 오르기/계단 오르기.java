import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    // Initialize stairs
    int[] stairs = new int[n+1];
    for (int i = 1; i <= n; i++) {
      stairs[i] = Integer.parseInt((in.readLine()));
    }

    // maxPoints to record maximum point on that step
    int[][] maxPoints = new int[n+1][2];

    // Fill the table (-1 point for forbiden cases)
    for (int i = 1; i <= n; i++) {
      // one stair up
      maxPoints[i][0] = maxPoints[i-1][1] + stairs[i];
      
      // two stairs up
      // if first stair, consider as two step from value 0
      maxPoints[i][1] = 
        (i >= 2) ? Math.max(maxPoints[i-2][0], maxPoints[i-2][1]) + stairs[i]
          : stairs[i];
    }

    // Print the maximum value
    System.out.println(Math.max(maxPoints[n][0], maxPoints[n][1]));
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}