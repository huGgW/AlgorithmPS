import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            String[] tmp = in.readLine().split(" ");

            int x1 = Integer.parseInt(tmp[0]);
            int y1 = Integer.parseInt(tmp[1]);
            int r1 = Integer.parseInt(tmp[2]);
            int x2 = Integer.parseInt(tmp[3]);
            int y2 = Integer.parseInt(tmp[4]);
            int r2 = Integer.parseInt(tmp[5]);

            int available = 2;
            // two coordinates are same
            if (x1 == x2 && y1 == y2) {
                if (r1 == r2) {
                    available = -1;
                }
                else {
                    available = 0;
                }
            } else {
                double distance = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
                if (distance > (r1 + r2) || distance < Math.abs(r1 - r2)) {
                    available = 0;
                }
                else if (distance == (double)(r1 + r2) || distance == Math.abs(r1 - r2)) {
                    available = 1;
                }
                else {
                    available = 2;
                }
            }

            out.write(Integer.toString(available));
            out.newLine();
        }
        
        out.flush();
        out.close();
        in.close();
    }
}