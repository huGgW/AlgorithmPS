import java.util.*;

public class Main {
  public static void main(String[] args) {
    int n = (new Scanner(System.in)).nextInt();

    System.out.println(countQueens(n));
  }

  public static int countQueens(int n) {
    // Initialize Chess Plate
    int[][] chessPlate = new int[n][n];

    int cnt = cntQueensRec(chessPlate, 0, 0);
    return cnt;
  }

  public static int cntQueensRec(int[][] chessPlate, int floor, int cnt) {
    int n = chessPlate.length;
    if (floor == n) {return ++cnt;}
    for (int x = 0; x < n; x++) {
      if (chessPlate[floor][x] == 0) {
        place(chessPlate, floor, x);
        floor++;
        cnt = cntQueensRec(chessPlate, floor, cnt);
        floor--;
        remove(chessPlate, floor, x);
      }
    }
    return cnt;
  }

  public static void place(int[][] chessPlate, int i, int j) {
    for (int k = 0; k < chessPlate.length; k++) {
      chessPlate[k][j]++;
      chessPlate[i][k]++;
      if (isInRange(0, chessPlate.length-1, i-k)
        && isInRange(0, chessPlate.length-1, j-k)) {
          chessPlate[i-k][j-k]++;
      }
      if (isInRange(0, chessPlate.length-1, i+k)
        && isInRange(0, chessPlate.length-1, j+k)) {
          chessPlate[i+k][j+k]++;
      }
      if (isInRange(0, chessPlate.length-1, i-k)
        && isInRange(0, chessPlate.length-1, j+k)) {
          chessPlate[i-k][j+k]++;
      }
      if (isInRange(0, chessPlate.length-1, i+k)
        && isInRange(0, chessPlate.length-1, j-k)) {
          chessPlate[i+k][j-k]++;
      }
    }
    chessPlate[i][j] -= 5;
  }

  public static void remove(int[][] chessPlate, int i, int j) {
    for (int k = 0; k < chessPlate.length; k++) {
      chessPlate[k][j]--;
      chessPlate[i][k]--;
      if (isInRange(0, chessPlate.length-1, i-k)
        && isInRange(0, chessPlate.length-1, j-k)) {
          chessPlate[i-k][j-k]--;
      }
      if (isInRange(0, chessPlate.length-1, i+k)
        && isInRange(0, chessPlate.length-1, j+k)) {
          chessPlate[i+k][j+k]--;
      }
      if (isInRange(0, chessPlate.length-1, i-k)
        && isInRange(0, chessPlate.length-1, j+k)) {
          chessPlate[i-k][j+k]--;
      }
      if (isInRange(0, chessPlate.length-1, i+k)
        && isInRange(0, chessPlate.length-1, j-k)) {
          chessPlate[i+k][j-k]--;
      }
    }
    chessPlate[i][j] += 5;
  }

  public static boolean isInRange(int s, int b, int x) {
    return (s <= x && x <= b);
  }
}