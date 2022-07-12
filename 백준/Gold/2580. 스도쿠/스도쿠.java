import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    // Initiallize
    HashSet<Integer>[] col = new HashSet[9];
    HashSet<Integer>[] row = new HashSet[9];
    HashSet<Integer>[][] block = new HashSet[3][3];
    int[][] sdoku = new int[9][9];
    ArrayList<Map.Entry<Integer, Integer>> emptyList = new ArrayList<>();

    for (int i = 0; i < 9; i++) {
      col[i] = new HashSet<Integer>(9);
      row[i] = new HashSet<Integer>(9);
    }
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
      block[i][j] = new HashSet<Integer>(9);
    }

    // Set sdoku
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 9; i++) {
      String[] tmp = in.readLine().split(" ");
      for (int j = 0; j < 9; j++) {
        int n = Integer.parseInt(tmp[j]);
        sdoku[i][j] = n;
        if (n == 0) {
          emptyList.add(Map.entry(i, j));
        }
        else {
          col[i].add(n);
          row[j].add(n);
          block[i/3][j/3].add(n);
        }
      }
    }
    in.close();

    // Fill empty sections
    fillSdoku(0, col, row, block, sdoku, emptyList, false);

    // Debug
    // System.out.println("Debug");

    // Print out sdoku
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        out.write(Integer.toString(sdoku[i][j]) + " ");
      }
      out.newLine();
    }
    out.flush(); out.close();
  }

  public static boolean fillSdoku (
    int stage,
    HashSet<Integer>[] col,
    HashSet<Integer>[] row,
    HashSet<Integer>[][] block,
    int[][] sdoku,
    ArrayList<Map.Entry<Integer, Integer>> emptyList,
    boolean isDone
  ) {
    // get empty coord
    int i = emptyList.get(stage).getKey();
    int j = emptyList.get(stage).getValue();

    for (int x = 1; x <= 9; x++) {
      if (isSettable(col, row, block, i, j, x)) {
        setSdoku(col, row, block, sdoku, i, j, x);
        stage++;

        // if filled all of the blanks
        if (stage == emptyList.size()) { return true; }

        isDone = fillSdoku(stage, col, row, block, sdoku, emptyList, isDone);

        // only revert the process if filling up the blank is failed
        if (!isDone) {
          unsetSdoku(col, row, block, sdoku, i, j, x);
          stage--;
        }
      }
    }

    return isDone;
  }

  public static boolean isSettable (
    HashSet<Integer>[] col,
    HashSet<Integer>[] row,
    HashSet<Integer>[][] block,
    int i, int j, int x
  ) {

    return !((col[i])).contains(x)
      && !((row[j])).contains(x)
      && !((block[i/3][j/3])).contains(x);
  }

  public static void setSdoku (
    HashSet<Integer>[] col,
    HashSet<Integer>[] row,
    HashSet<Integer>[][] block,
    int[][] sdoku,
    int i, int j, int x
  ) {
    sdoku[i][j] = x;
    col[i].add(x);
    row[j].add(x);
    block[i/3][j/3].add(x);
  }

  public static void unsetSdoku (
    HashSet<Integer>[] col,
    HashSet<Integer>[] row,
    HashSet<Integer>[][] block,
    int[][] sdoku,
    int i, int j, int x
  ) {
    sdoku[i][j] = 0;
    col[i].remove(x);
    row[j].remove(x);
    block[i/3][j/3].remove(x);
  }
}