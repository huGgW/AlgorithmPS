import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(in.readLine());
            Point[] arr = new Point[n];

            for (int i = 0; i < n; i++) {
                String[] s = in.readLine().split(" ");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                arr[i] = new Point(x, y);
            }
            in.close();

            quickSortRandom(arr, 0, n-1);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            for (Point p : arr) {
                out.write(p.toString());
                out.newLine();
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void quickSortRandom(Point[] arr, int b, int e) {
        int len = e - b + 1;
        if (len <= 1) {
            return;
        }

        int pivIndex = (int)Math.floor(Math.random() * len) + b;
        swap(arr, e, pivIndex);

        Point piv = arr[e];
        int i = b - 1;
        for (int j = b; j < e; j++) {
            if (arr[j].isSmallerThan(piv)) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, e);
        quickSortRandom(arr, b, i-1);
        quickSortRandom(arr, i+1, e);
    }

    static void swap(Point[] arr, int i, int j) {
        Point tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
class Point {
    int x;
    int y;
    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
    boolean isSmallerThan (Point other) {
        if (this.x == other.x) {
            return (this.y < other.y);
        }
        else { return (this.x < other.x); }
    }
    @Override
    public String toString() {
        return (Integer.toString(this.x) + " " + Integer.toString(this.y));
    }
}