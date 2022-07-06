import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] points = new double[n];
        double max = 0;
        for (int i = 0; i < n; i++) {
            points[i] = (double)(sc.nextInt());
            if (points[i] > max) {
                max = points[i];
            }
        }
        for (int i = 0; i < n; i++) {
            points[i] = (points[i] / max) * 100;
        }
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += points[i];
        }
        System.out.println(result/n);
    }
}