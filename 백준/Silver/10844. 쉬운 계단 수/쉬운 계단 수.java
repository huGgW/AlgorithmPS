import java.util.*;

public class Main {
  public static void main(String[] args) {
    int n = (new Scanner(System.in)).nextInt();

    // 각 n자리 마지막 자리수 따라, n-1자리까지의 마지막 자리수 따른 
    // 계단수 중 해당하는 것들의 갯수 더해서 표 채움.

    int[][] stairNum = new int[n][10];
    int lim = 1000000000;

    for (int i = 1; i <= 9; i++) {
      stairNum[0][i] = 1;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= 9; j++) {
        switch (j) {
        case 0: {
          stairNum[i][j] = stairNum[i-1][j+1];
          break;
        }
        case 9: {
          stairNum[i][j] = stairNum[i-1][j-1];
          break;
        }
        default: {
          stairNum[i][j] = (stairNum[i-1][j-1] + stairNum[i-1][j+1]) % lim;
          break;
        }
        }
      }
    }

    // amount of N length stair number
    int totalCnt = 0;
    for (int x : stairNum[n-1]) {
      totalCnt = (totalCnt + x) % lim;
    }

    System.out.println(totalCnt);
  }
}