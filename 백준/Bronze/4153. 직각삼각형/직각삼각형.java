import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            String[] tmp = in.readLine().split(" ");
            int[] vertices = new int[3];

            int maxIdx = -1;
            int termIndicator = 0;
            for (int i = 0; i < 3; i++) {
                int x = Integer.parseInt(tmp[i]);
                if (x == 0) termIndicator++;
                vertices[i] = x;
                if (maxIdx == -1 || vertices[maxIdx] < x) {
                    maxIdx = i;
                }
            }
            if (termIndicator == 3) break;

            int pitagoras = 0;
            for (int i = 0; i < 3; i++) {
                if (maxIdx != i) {
                    pitagoras += (vertices[i] * vertices[i]);
                }
            }
            if (vertices[maxIdx] * vertices[maxIdx] == pitagoras) {
                out.write("right\n");
            }
            else {
                out.write("wrong\n");
            }
        }
        out.flush();
        in.close();
        out.close();
    }
}