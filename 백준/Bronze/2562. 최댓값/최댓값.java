import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        int n = 0;
        int place = 0;
        
        Scanner sc = new Scanner(System.in);
        int iter = 9;
        for (int i = 0; i < iter; i++) {
            int num = sc.nextInt();
            if (num > n) {
                n = num;
                place = i + 1;
            }
        }

        System.out.println(n + "\n" + place);
    }
}