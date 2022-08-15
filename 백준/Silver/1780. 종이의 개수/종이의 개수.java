import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[][] papers = new int[n][n];
        for (int i = 0; i < n; i++) {
            papers[i] = Arrays.stream(
                    in.readLine().split(" ")
                ).mapToInt(
                    x -> Integer.parseInt(x)
                ).toArray();
        }

        // Count (-1 at 0, 0 at 1, 1 at 2)
        int[] cnt = new int[3];
        countPaper(0, n-1, 0, n-1, papers, cnt);

        // Print
        for (int c : cnt) {
            System.out.println(c);
        }
    }

    static void countPaper(int xb, int xe, int yb, int ye, int[][] papers, int[] cnt) {
        if (isCollapsable(xb, xe, yb, ye, papers)) {
            cnt[papers[yb][xb]+1]++;
        } else {
            // devide into 9 squares
            int squareLen = (xe - xb + 1) / 3;
            int x1over = xb + squareLen;
            int x2under = xe - squareLen;
            int y1over = yb + squareLen;
            int y2under = ye - squareLen;

            // count each of squares
            countPaper(xb, x1over-1, yb, y1over-1, papers, cnt);
            countPaper(x1over, x2under, yb, y1over-1, papers, cnt);
            countPaper(x2under+1, xe, yb, y1over-1, papers, cnt);
            countPaper(xb, x1over-1, y1over, y2under, papers, cnt);
            countPaper(x1over, x2under, y1over, y2under, papers, cnt);
            countPaper(x2under+1, xe, y1over, y2under, papers, cnt);
            countPaper(xb, x1over-1, y2under+1, ye, papers, cnt);
            countPaper(x1over, x2under, y2under+1, ye, papers, cnt);
            countPaper(x2under+1, xe, y2under+1, ye, papers, cnt);
        }
    }

    static boolean isCollapsable(int xb, int xe, int yb, int ye, int[][] papers) {
        for (int i = yb; i <= ye; i++) {
            for (int j = xb; j <= xe; j++) {
                if (papers[i][j] != papers[yb][xb]) {
                    return false;
                }
            }
        }
        return true;
    }
}
