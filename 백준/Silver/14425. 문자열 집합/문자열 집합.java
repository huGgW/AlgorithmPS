import java.io.*;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = in.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);

        HashSet<String> set = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            set.add(in.readLine());
        }

        int cnt = 0;
        for (int i = n; i < (n + m); i++) {
            if (set.contains(in.readLine())) {
                cnt++;
            }
        }
        in.close();

        System.out.println(cnt);
    }
}