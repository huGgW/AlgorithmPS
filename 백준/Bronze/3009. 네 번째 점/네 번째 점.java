import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> xSet = new HashSet<>(2);
        HashSet<Integer> ySet = new HashSet<>(2);

        for (int i = 0; i < 3; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (xSet.contains(x)) {
                xSet.remove(x);
            } else {
                xSet.add(x);
            }

            if (ySet.contains(y)) {
                ySet.remove(y);
            } else {
                ySet.add(y);
            }
        }
        sc.close();

        System.out.println(xSet.toArray()[0] + " " + ySet.toArray()[0]);
    }
}