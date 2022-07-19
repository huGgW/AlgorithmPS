import java.io.*;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    int[] wines = new int[n+1];
    for (int i = 1; i <= n; i++) {
      wines[i] = Integer.parseInt(in.readLine());
    }
    
    int[] maxWineSet = new int[n+1];
    for (int i = 1; i <= n; i++) {
      if (i == 1) {
        maxWineSet[1] = wines[1];
      }
      else if (i == 2) {
        maxWineSet[2] = wines[1] + wines[2];
      }
      else {
        maxWineSet[i] = Arrays.stream(
          new int[]{
            maxWineSet[i-1], 
            maxWineSet[i-2] + wines[i], 
            maxWineSet[i-3] + wines[i-1] + wines[i]
          }
        ).max().getAsInt();
      }
    }

    System.out.println(maxWineSet[n]);

  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}