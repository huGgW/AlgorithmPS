import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] plate = new char[m][n];
        char[][] white = new char[8][8];
        char[][] black = new char[8][8];

        // Initialize white and black
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                white[i] = new char[]{'W', 'B','W', 'B','W', 'B','W', 'B'};
                black[i] = new char[]{'B','W', 'B','W', 'B','W', 'B', 'W'};
            } else {
                white[i] = new char[]{'B','W', 'B','W', 'B','W', 'B', 'W'};
                black[i] = new char[]{'W', 'B','W', 'B','W', 'B','W', 'B'};
            }
        }

        // Initialze the plate
        for (int i = 0; i < m; i++) {
            String line = sc.next();
            for (int j = 0; j < n; j++) {
                plate[i][j] = line.charAt(j);
            }
        }
        sc.close();

        // Find the minimum when white
        int min_white = findMin(m, n, plate, white);
        // Find the minimum when black
        int min_black = findMin(m, n, plate, black);
        System.out.println(Integer.min(min_white, min_black));
    }
    static int findMin(int m, int n, char[][] plate, char[][] ans) {
        int del_m = m - 8;
        int del_n = n - 8;
        int min = -1;
        for (int i = 0; i <= del_m; i++) {
            for (int j = 0; j <= del_n; j++) {
                int cnt = 0;
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (plate[x+i][y+j] != ans[x][y]) {
                            cnt++;
                        }
                    }
                }
                if (min == -1 || min > cnt) {
                    min = cnt;
                }
            }
        }
        return min;
    }
} 