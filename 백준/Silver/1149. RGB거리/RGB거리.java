/*
 * f(r, n) = min{f(g, n-1), f(b, n-1)} + nth house's cost of r
 * same for green, blue
 */

import java.io.*;
import java.util.*;

public class Main {
  public static void main (String[] args) {
  try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // Initialize Houses
    int n = Integer.parseInt(in.readLine());
    // for include 0 and n+1 for easy algorithm
    House[] houses = new House[n+1]; 
    houses[0] = new House();
    for (int i = 1; i <= n; i++) {
      houses[i] = new House(in.readLine());
    }

    // Fill the table using the recurrence
    int[][] minCost = new int[n+1][3]; // for convenience of algorithm
    for (int i = 1; i <= n; i++) for (int c = 0; c < 3; c++)  {
      // Get current house cost
      int currCost = houses[i].getCost(Color.idxToColor(c));
      // Get minimum available cost of past houses
      int pastCost = -1;
      for (int k = 0; k < 3; k++) {
        if (k != c) {
          pastCost = (pastCost == -1 || pastCost > minCost[i-1][k])
            ? minCost[i-1][k] : pastCost;
        }
      }
      // Fill the table
      minCost[i][c] = currCost + pastCost;
    }

    System.out.println(Math.min(Math.min(minCost[n][0], minCost[n][1]), minCost[n][2]));

    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
  }
  catch (IOException e) {
    e.printStackTrace();
  }
  }
}

enum Color {
  R, G, B, NULL;

  public int getIdx() {
    switch (this) {
    case R: { return 0; }
    case G: { return 1; }
    case B: { return 2; }
    default: { return -1; }
    }
  }
  public static Color idxToColor(int i) {
    switch (i) {
    case 0 : { return R; }
    case 1 : { return G; }
    case 2 : { return B; }
    default: { return NULL; }
    }
  }
}

class House {
  private int RCost;
  private int GCost;
  private int BCost;
  private Color color = Color.NULL;

  public House() {
    RCost = GCost = BCost = 0;
  }
  public House(int rc, int gc, int bc) {
    RCost = rc; GCost = gc; BCost = bc;
  }

  public House(String costString) {
    int[] costs = Arrays.stream(
      costString.split(" "))
      .mapToInt(s -> Integer.parseInt(s))
      .toArray();

    RCost = costs[0]; GCost = costs[1]; BCost = costs[2];
  }

  public int getCost(Color c) {
    switch (c) {
    case R: {
      return RCost;
    }
    case G: {
      return GCost;
    }
    case B: {
      return BCost;
    }
    default: {
      return 0;
    }
    }
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color c) {
    color = c;
  }
}