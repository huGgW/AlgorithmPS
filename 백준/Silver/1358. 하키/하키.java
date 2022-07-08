import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = in.readLine().split(" ");
        int w = Integer.parseInt(tmp[0]);
        int h = Integer.parseInt(tmp[1]);
        int x = Integer.parseInt(tmp[2]);
        int y = Integer.parseInt(tmp[3]);
        int p = Integer.parseInt(tmp[4]);

        Square sq = new Square(x, y, w, h);
        Circle c1 = new Circle(x, (y+h/2), h/2);
        Circle c2 = new Circle((x+w), (y+h/2), h/2);

        int cnt = 0;
        for (int i = 0; i < p; i++) {
            tmp = in.readLine().split(" ");
            Point pnt = new Point (
                Integer.parseInt(tmp[0]),
                Integer.parseInt(tmp[1])
            );
            if (isIn(pnt, sq, c1, c2)) {
                cnt++;
            }
        }

        out.write(Integer.toString(cnt));
        out.flush();
    }
    public static boolean isIn(Point p, Square sq, Circle c1, Circle c2) {
        return (
            sq.isIn(p) || c1.isIn(p) || c2.isIn(p)
        );
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
class Square {
    public int x;
    public int y;
    public int width;
    public int height;

    public Square(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isIn(Point p) {
        return ((this.x <= p.x && p.x <= (this.x + width))
            && (this.y <= p.y && p.y <= (this.y + height)));
    }
}