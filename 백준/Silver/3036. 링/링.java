// import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
      try {
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       int size = Integer.parseInt(in.readLine()) - 1;
       int[] rings = new int[size];

       String[] tmp = in.readLine().split(" ");
       in.close();
       int fstRing = Integer.parseInt(tmp[0]);
       for (int i = 0; i < size; i++) {
        rings[i] = Integer.parseInt(tmp[i+1]);
       }

       BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
       for (int x : rings) {
        printCycle(fstRing, x, out);
       }
       out.flush();
       out.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    public static void printCycle(int fstRing, int otherRing, BufferedWriter out) throws IOException {
      int gcd = getGCD(fstRing, otherRing);

      out.write(
        Integer.toString(fstRing / gcd)
        + "/" +
        Integer.toString(otherRing / gcd)
        + "\n"
      );
    }
    public static int getGCD(int a, int b) {
      int smaller = (a > b) ? b : a;
      int bigger = (a + b) - smaller;

      if (smaller == 0) { return bigger; }
      else {
        return getGCD(smaller, bigger % smaller);
      }
    }
}