import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(in.readLine());
    
    for (int k = 0; k < t; k++) {
      int[] tmp = Arrays.stream(in.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
      int n = tmp[0]; 
      int subject = tmp[1];

      int[] documents = Arrays.stream(
        in.readLine().split(" ")
      ).mapToInt(x -> Integer.parseInt(x)
      ).toArray();
      ArrayDeque<Integer> printer = new ArrayDeque<>(n);
      PriorityQueue<Integer> track = new PriorityQueue<>(Comparator.reverseOrder());

      for (int i = 0; i < n; i++) {
        printer.add(i);
        track.add(documents[i]);
      }

      int cnt = 0;
      while (!printer.isEmpty()) {
        int x = printer.remove();
        if (documents[x] >= track.peek()) {
          track.poll();
          cnt++;
          if (x == subject) { out.write(Integer.toString(cnt)); out.newLine(); break; }
        } else {
          printer.add(x);
        }
      }
    }

    out.flush();
  }
}