/*
 * for n = 1 -> 1
 * for n = 2 -> 2
 * else -> f(n-1) + f(n-2) // add 1 on front and add 00 on front
 */

import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();

    int count00 = calcCount00(n, 15746);
    
    System.out.println(count00);
  }
  public static int calcCount00(int n, int divider) {
    if (n <= 2) return n;

    int[] cntTable = new int[n];

    cntTable[0] = 1;
    cntTable[1] = 2;
    for (int i = 2; i < n; i++) {
      cntTable[i] = (cntTable[i-1] + cntTable[i-2]) % divider;
    }

    return cntTable[n-1];
  }
}