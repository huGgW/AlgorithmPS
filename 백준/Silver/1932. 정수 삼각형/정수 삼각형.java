import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    // make triangle
    ArrayList<ArrayList<Integer>> triangle = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      triangle.add(
        (ArrayList<Integer>)
        Arrays.stream(
          in.readLine().split(" ")
        ).map(s -> Integer.parseInt(s))
        .collect(Collectors.toList())
      );
    }
    System.out.print("");

    // Get maximum sum by filling table (Reuse triangle)
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= i; j++) {
        int fromLeft;
        try {
          fromLeft = triangle.get(i-1).get(j-1);
        }
        catch (IndexOutOfBoundsException e) {
          fromLeft = 0;
        }
        int fromRight;
        try {
          fromRight = triangle.get(i-1).get(j);
        }
        catch (IndexOutOfBoundsException e) {
          fromRight = 0;
        }

        int before = Math.max(fromLeft, fromRight);
        triangle.get(i).set(j, before + triangle.get(i).get(j));
      }
    }

    System.out.println(Collections.max(triangle.get(n-1)));
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}