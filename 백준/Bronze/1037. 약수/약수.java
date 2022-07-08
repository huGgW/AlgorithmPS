import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int max = sc.nextInt();
        int min = max;
        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            max = (x > max) ? x : max;
            min = (x < min) ? x : min;
        }

        System.out.print(max * min);
    }
}