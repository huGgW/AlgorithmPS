import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] tmp = in.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);

            if (x == 0 && y == 0) {
                break;
            }
            else if (x%y == 0) {
                out.write("multiple"); out.newLine();
            }
            else if (y%x == 0) {
                out.write("factor"); out.newLine();
            }
            else {
                out.write("neither"); out.newLine();
            }
        }
        out.flush();
    }
}