import java.io.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = in.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        HashSet<String> 듣 = new HashSet<>(n);
        HashSet<String> 보 = new HashSet<>(m);

        for (int i = 0; i < n; i++) {
            듣.add(in.readLine());
        }
        for (int i = 0; i < m; i++) {
            보.add(in.readLine());
        }
        in.close();

        듣.retainAll(보);
        ArrayList<String> 듣보 = new ArrayList<>(듣);
        Collections.sort(듣보);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.write(Integer.toString(듣보.size())); out.newLine();
        for (String s: 듣보) {
            out.write(s); out.newLine();
        }

        out.flush();
        out.close();
    }
}