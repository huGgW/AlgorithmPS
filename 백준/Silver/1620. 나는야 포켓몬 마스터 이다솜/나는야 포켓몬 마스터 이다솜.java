import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = in.readLine().split(" ");

        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        String[] numToName = new String[n];
        HashMap<String, Integer> nameToNum = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            String name = in.readLine();
            numToName[i] = name;
            nameToNum.put(name, i+1);
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            String s = in.readLine();
            try {
                int num = Integer.parseInt(s);
                out.write(numToName[num-1]);
                out.newLine();
            }
            catch (NumberFormatException e) {
                int num = nameToNum.get(s);
                out.write(Integer.toString(num));
                out.newLine();
            }
        }
        out.flush();
        in.close();
        out.close();
    }
}