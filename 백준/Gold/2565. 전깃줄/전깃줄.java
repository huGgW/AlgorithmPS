/*
 * A를 sort했을 때 겹치는 전선이 없는 경우
 * B 역시 sorted 되어 있어야 함.
 * 즉 A를 sort했을 때 B에서 Longest Increasing SubSequence를 제외하고 제거한 것이
 * 최소로 전기줄 제거하는 방법
 */

import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());

    int[] A = new int[n];
    int[] B = new int[n];
    for (int i = 0; i < n; i++) {
      int[] points = Arrays.stream(
        in.readLine().split(" ")
      ).mapToInt(
        x -> Integer.parseInt(x)
      ).toArray();

      A[i] = points[0];
      B[i] = points[1];
    }

    // Sort A, and move B according to sorting
    Random rand = new Random();
    sortAccordingTo(A, B, 0, n-1, rand);

    // Find length of LIS of B and print (n-LIS)
    System.out.println((n - findLIS(B)));
  }
  catch (IOException e) {
    e.printStackTrace();
  }

  }

  static void sortAccordingTo(int[] A, int[] B, int b, int e, Random rand) {
    if (b >= e) return;
    
    int piv = rand.nextInt(e-b+1) + b;
    swap(A, e, piv);
    swap(B, e, piv);

    int i = b-1;
    for (int j = b; j < e; j++) {
      if (A[j] < A[e]) {
        swap(A, ++i, j);
        swap(B, i, j);
      }
    }

    swap(A, ++i, e);
    swap(B, i, e);

    sortAccordingTo(A, B, b, i-1, rand);
    sortAccordingTo(A, B, i+1, e, rand);
  }

  static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  static int findLIS(int[] nums) {
    int n = nums.length;

    int[] decreasingRevertFromCnt = new int[n];
    decreasingRevertFromCnt[0] = 1;

    for (int i = 1; i < n; i++) {
      int cnt = 0;
      for (int j = i-1; j >= 0; j--) {
        if (nums[j] < nums[i] && decreasingRevertFromCnt[j] > cnt) {
          cnt = decreasingRevertFromCnt[j];
        }
      }
      decreasingRevertFromCnt[i] = ++cnt;
    }

    return Arrays.stream(decreasingRevertFromCnt).max().getAsInt();
  }
}