import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        boolean[] isSelf = new boolean[10000];
        Arrays.fill(isSelf, true);
        for (int i = 0; i < isSelf.length; i++) {
            if (isSelf[i]) {
                apply_d(i+1, isSelf);
            }
        }
        for (int i = 0; i < isSelf.length; i++) {
            if (isSelf[i]) {
                System.out.println((i+1));
            }
        }
    }
    static int d(int n) {
        int result = n;
        while (n > 0) {
            result += n % 10;
            n /= 10;
        }
        result += n;
        return result;
    }
    static void apply_d(int n, boolean[] isSelf) {
        while((n = d(n)) <= isSelf.length) {
            if (isSelf[n-1]) {
                isSelf[n-1] = false;
            }
            else {
                break;
            }
        }
    }
}