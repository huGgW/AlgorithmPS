import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        HashMap<Integer, Integer> cards = new HashMap<>(n);

        String[] tmp = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(tmp[i]);
            if (cards.get(num) == null) {
                cards.put(num, 1);
            }
            else {
                cards.replace(num, cards.get(num) + 1);
            }
        }

        int m = Integer.parseInt(in.readLine());
        tmp = in.readLine().split(" ");
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(tmp[i]);
            out.write(Integer.toString(cards.getOrDefault(num, 0)) + " ");
        }
        out.flush();
        in.close();
        out.close();
    }
}