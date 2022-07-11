import java.io.*;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
      int num = Integer.parseInt(in.readLine());

      for (int i = 0; i < num; i++) {
        HashMap<String, Integer> clothes = new HashMap<>();
        int n = Integer.parseInt(in.readLine());

        for (int j = 0; j < n; j++) {
          String[] tmp = in.readLine().split(" ");

          if (clothes.get(tmp[1]) == null) {
            clothes.put(tmp[1], 1);
          } else {
            clothes.put(tmp[1], clothes.get(tmp[1]) + 1);
          }
        }

        int result = 1;
        for (int cnt : clothes.values()) {
          result *= (cnt + 1);
        }
        result--;

        out.write(Integer.toString(result));
        out.newLine();
      }
      in.close();
      out.flush();
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}