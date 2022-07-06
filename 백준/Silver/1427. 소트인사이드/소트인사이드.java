import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        ArrayList<Integer> digits = makeDigits(n);

        ArrayList<Integer> sorted = reverseCountSort(digits);
        for (int x : sorted) {
            System.out.print(x);
        }
    }
    static ArrayList<Integer> makeDigits(int n) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        return digits;
    }
    static ArrayList<Integer> reverseCountSort(ArrayList<Integer> digits) {
        int[] cnt = new int[10];

        for (int x : digits) {
            cnt[x]++;
        }

        for (int i = 8; i >= 0; i--) {
            cnt[i] += cnt[i+1];
        }

        ArrayList<Integer> sorted = new ArrayList<>(Collections.nCopies(digits.size(), 0));
        for (int i = digits.size()-1; i >= 0; i--) {
            int x = digits.get(i);
            cnt[x]--;
            sorted.set(cnt[x], x);
        }

        return sorted;
    }
}