import java.io.*;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        HashSet<Integer> cards = new HashSet<>(n);

        String[] tmp = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cards.add(Integer.parseInt(tmp[i]));
        }

        int m = Integer.parseInt(in.readLine());
        int[] has = new int[m];

        tmp = in.readLine().split(" ");
        in.close();
        for (int i = 0; i < m; i++) {
            has[i] = (cards.contains(Integer.parseInt(tmp[i])))
                        ? 1 : 0;
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int x : has) {
            out.write(Integer.toString(x) + " ");
        }
        out.flush();
        out.close();
    }
}