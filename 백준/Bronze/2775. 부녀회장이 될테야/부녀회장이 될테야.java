import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int k = sc.nextInt();
            int n = sc.nextInt();
            int[][] residents = new int[k+1][n+1];
            
            for (int j = 1; j <= n; j++) {
                residents[0][j] = j;
            }

            for (int f = 1; f <= k; f++) {
                for (int j = 1; j <= n; j++) {
                    residents[f][j] = residents[f-1][j] + residents[f][j-1];
                }
            }

            System.out.println(residents[k][n]);
        }
    }
}