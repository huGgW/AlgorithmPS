import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[][] video = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                video[i][j] = Integer.parseInt(Character.toString(in.read()));
            }
            in.readLine();
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        encodeVideo(0, n-1, 0, n-1, video, out);
        out.flush();
    }

    static void encodeVideo(int xb, int xe, int yb, int ye, int[][] video, BufferedWriter out) throws IOException {
        if (isEncodable(xb, xe, yb, ye, video)){
            out.write(Integer.toString(video[yb][xb]));
        } else {
            out.write("(");

            int xm = (xb + xe) / 2;
            int ym = (yb + ye) / 2;

            // LeftTop
            encodeVideo(xb, xm, yb, ym, video, out);

            // Rightop
            encodeVideo(xm+1, xe, yb, ym, video, out);

            // LeftBottom
            encodeVideo(xb, xm, ym+1, ye, video, out);

            // RightBottom
            encodeVideo(xm+1, xe, ym+1, ye, video, out);

            out.write(")");
        }
    }

    static boolean isEncodable(int xb, int xe, int yb, int ye, int[][] video) {
        boolean zipped = true;
        LOOP:
        for (int i = yb; i <= ye; i++) {
            for (int j = xb; j <= xe; j++) {
                if (video[i][j] != video[yb][xb]) {
                    zipped = false;
                    break LOOP;
                }
            }
        }
        return zipped;
    }
}
