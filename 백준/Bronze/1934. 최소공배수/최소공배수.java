import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(in.readLine());

        for (int i = 0; i < num; i++) {
            String[] tmp = in.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int m = Integer.parseInt(tmp[1]);
            int gcm = getGCM(n, m);

            out.write(Integer.toString(n * m / gcm));
            out.newLine();
        }
        out.flush();
    }
    public static int getGCM(int n, int m) {
        int smaller = (n > m) ? m : n;
        int bigger = (m + n) - smaller;

        int remainder = bigger % smaller;
        if (remainder == 0) { return smaller; }
        else { return getGCM(smaller, remainder); }
    }
}