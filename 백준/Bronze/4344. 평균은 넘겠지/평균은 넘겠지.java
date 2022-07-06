import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int[] pnt = new int[m];
            double aver = 0;
            for (int j = 0; j < m; j++) {
                pnt[j] = sc.nextInt();
                aver += (double)pnt[j];
            }
            aver /= m;
            int top = 0;
            for (int j = 0; j < m; j++) {
                if (pnt[j] > aver) {
                    top++;
                }
            }
            System.out.println(String.format("%.3f%%", (((double)top/m) * 100)));
        }
    }
}