import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    printSequence(n, m);
  }
  public static void printSequence(int n, int m) {
    try {
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

      HashSet<Integer> printed = new HashSet<>(m);
      int[] sequence = new int[m];
      int pos = 0;

      printSeqRec(n, m, pos, sequence, printed, out);
      out.flush();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void printSeqRec
    (int n, int m, int pos, int[] sequence, HashSet<Integer> printed, BufferedWriter out)
    throws IOException {

      for (int i = 1; i <= n; i++) {
        if (!printed.contains(i)) {
          sequence[pos] = i;
          printed.add(i);
          pos++;

          if (pos == m) {
            for (int x : sequence) {
              out.write(Integer.toString(x) + " ");
            }
            out.newLine();
          }
          else {
            printSeqRec(n, m, pos, sequence, printed, out);
          }
          pos--;
          printed.remove(i);
        }
      }
    }
}