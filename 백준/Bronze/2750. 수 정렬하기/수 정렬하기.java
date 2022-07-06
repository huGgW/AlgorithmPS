import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int j = i - 1;
            while (true) {
                if (j >= 0 && x < arr[j]) {
                    arr[j+1] = arr[j];
                    j--;
                }
                else {
                    arr[j+1] = x;
                    break;
                }
            }

        }
        sc.close();

        for (int x : arr) {
            System.out.println(x);
        }
    }
}