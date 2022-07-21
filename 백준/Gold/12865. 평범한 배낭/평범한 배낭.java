/*
 * 어떤 set에서의 무게 W 제한의 최대 가치는
 * 이 물건을 넣을 경우으 V + 물건 제외 set에서의 무게 W-w 제한의 최대 가치
 * 혹은 이 물건을 넣지 않ㅇ르 경우의 물건제외 set에서의 무게W 제한의 최대 가치이다.
 */
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrimitiveIterator.OfInt tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).iterator();
    int n = tmp.next();
    int k = tmp.next();

    Thing[] things = new Thing[n];
    for (int i = 0; i < n; i++) {
      tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).iterator();
      things[i] = new Thing(tmp.next(), tmp.next());
    }

    // saves max value of (ith, weight)
    int[][] maxValues = new int[n][k+1];
    for (int i = 0; i < n; i++) {
      Arrays.fill(maxValues[i], -1);
    }

    // fill the data structure
    System.out.println(getMaxValue(n-1, k, things, maxValues));
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }

  static int getMaxValue(int i, int w, Thing[] things, int[][] maxValues) {
    // already filled
    if (maxValues[i][w] != -1) {
      return maxValues[i][w];
    }
    // not filled
    // Debug
    // System.out.println(String.format("===== calculation : i %d, w %d =====", i, w));
    int restWeight = w - things[i].weight;
    // base case
    if (i == 0) {
      if (restWeight >= 0) {
        maxValues[0][w] = things[i].value;
        return things[i].value;
      }
      else {
        return 0;
      }
    }
    else {
      int exclude = getMaxValue(i-1, w, things, maxValues);
      int include = 0;
      
      if (restWeight >= 0) {
        include = getMaxValue(i-1, restWeight, things, maxValues) + things[i].value;
      }

      maxValues[i][w] = Math.max(include, exclude);
      return Math.max(include, exclude);
    }
  }
}

class Thing {
  int weight;
  int value;

  public Thing(int w, int v) {
    weight = w; value = v;
  }
}