import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        sc.close();

        int xDist = ((double)w/2 > x) ? x : (w - x);
        int yDist = ((double)h/2 > y) ? y : (h - y);
        System.out.println((xDist < yDist) ? xDist : yDist);
    }
}