import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    int[][] S = new int [n][n];
    for (int i = 0; i < n; i++) {
      S[i] = Arrays.stream(
        in.readLine().split(" ")
      ).mapToInt(x -> Integer.parseInt(x))
      .toArray();
    }

    // Declare teams, initiallize total set
    HashSet<Integer> fst = new HashSet<>(n/2);
    HashSet<Integer> snd = new HashSet<>(n/2);
    HashSet<Integer> full = new HashSet<>(n);
    for (int i = 0; i < n; i++) {
      full.add(i);
    }

    // Calculate all cases and return minimum
    int minimum = calcMin(S, -1, -1, fst, snd, full);
    System.out.println(minimum);
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }

  public static int calcMin(int[][] S, int min, int base, HashSet<Integer> fst, HashSet<Integer> snd, HashSet<Integer> full) {
    int n = S.length;
    for (int i = base+1; i < n; i++) {
      if (!fst.contains(i)) {
        fst.add(i);
        
        if (fst.size() == n/2) {
          snd = (HashSet<Integer>)full.clone();
          snd.removeAll(fst);

          int tmp = calculateDiff(S, fst, snd);
          min = (min != -1) ? Math.min(tmp, min) : tmp;
        }
        else {
          min = calcMin(S, min, i, fst, snd, full);
        }

        fst.remove(i);
      }
    }
    return min;
  }

  public static int calculateDiff(int[][] S, HashSet<Integer> fst, HashSet<Integer> snd) {
    int fstResult = 0, sndResult = 0;

    for (int i : fst) for (int j : fst) {
      fstResult += S[i][j];
    }
    for (int i : snd) for (int j : snd) {
      sndResult += S[i][j];
    }

    return Math.abs(fstResult - sndResult);
  }
}