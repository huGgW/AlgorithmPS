import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(in.readLine());
        
        for (int i = 0; i < n; i++) {
            String[] tmp = in.readLine().split(" ");
            Point src = new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
            Point dst = new Point(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));

            int m = Integer.parseInt(in.readLine());
            Circle[] circles = new Circle[m];
            boolean[] shouldThrough = new boolean[m];
            for (int j = 0; j < m; j++) {
                tmp = in.readLine().split(" ");
                circles[j] = new Circle (
                    Integer.parseInt(tmp[0]),
                    Integer.parseInt(tmp[1]),
                    Integer.parseInt(tmp[2])
                );
            }

            for (int j = 0; j < m; j++) {
                if (circles[j].isIn(src)) {
                    shouldThrough[j] = !shouldThrough[j];
                }
                if (circles[j].isIn(dst)) {
                    shouldThrough[j] = !shouldThrough[j];
                }
            }

            int cnt = 0;
            for (boolean b: shouldThrough) {
                if (b) cnt++;
            }

            out.write(Integer.toString(cnt)); out.newLine();
        }

        out.flush();
        in.close();
        out.close();
    }
}
class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x; this.y = y;
    }

    public double distance(Point p) {
        return Math.sqrt(
            Math.pow((this.x - p.x), 2) + Math.pow((this.y - p.y), 2)
        );
    }
}
class Circle {
    public Point c;
    public int r;

    public Circle(int x, int y, int r) {
        c = new Point(x, y);
        this.r = r;
    }

    public boolean isIn(Point p) {
        return (p.distance(c) <= r);
    }
}