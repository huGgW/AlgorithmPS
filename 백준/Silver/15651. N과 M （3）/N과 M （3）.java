import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();
    sc.close();

    printSequence(n, m);
  }
  public static void printSequence(int n, int m) {
    try {
      int[] sequence = new int[m];
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
      
      printSeqRec(n, m, 0, sequence, out);

      out.flush();
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void printSeqRec
    (int n, int m, int pos, int[] sequence, BufferedWriter out)
    throws IOException {

    for (int i = 1; i <= n; i++) {
      sequence[pos++] = i;

      if (pos == m) {
        for (int x : sequence) {
          out.write(Integer.toString(x) + " ");
        }
        out.newLine();
      }
      else {
        printSeqRec(n, m, pos, sequence, out);
      }

      pos--;
    }
  }
}