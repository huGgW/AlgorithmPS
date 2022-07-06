import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        int fst = 665;
        int ver = 0;

        while (ver != n) {
            if (is666(Integer.toString(++fst).toCharArray())) {
                ver++;
            }
        }

        System.out.println(fst);
    }
    static boolean is666(char[] digits) {
        int cnt = 0;
        for (char c : digits) {
            if (c == '6') { 
                cnt++;
                if (cnt >= 3) { break; }
            }
            else { cnt = 0; }
        }
        return (cnt >= 3);
    }
}