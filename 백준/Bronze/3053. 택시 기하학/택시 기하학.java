import java.util.Scanner;
public class Main {
    public static void main (String[] args) {
        int r = (new Scanner(System.in)).nextInt();

        double euclidean = Math.PI * r * r;
        double taxi = (double)r * r * 2.0;

        System.out.println(String.format("%.6f", euclidean));
        System.out.println(String.format("%.6f", taxi));
    }
}