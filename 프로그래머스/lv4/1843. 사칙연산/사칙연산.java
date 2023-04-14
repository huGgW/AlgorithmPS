import java.util.*;
class Solution {
    public int solution(String arr[]) throws Exception {
        int n = arr.length;
        int[][] min = new int[n][n];
        int[][] max = new int[n][n];
        
        return calcMax(0, n-1, arr, min, max);
    }
    
    public boolean isPlus(String s) {
        return s.equals("+");
    }
    
    public int calcMax(int b, int e, String[] arr, int[][] min, int[][] max) throws Exception {
        if (max[b][e] != 0) {
            return max[b][e];
        } else if (b == e) {
            max[b][b] = Integer.parseInt(arr[b]);
            min[b][b] = Integer.parseInt(arr[b]);
            return max[b][b];
        } else if (e - b == 2) {
            int left = Integer.parseInt(arr[b]);
            boolean isAddition = isPlus(arr[b+1]);
            int right = Integer.parseInt(arr[b+2]);
            int calc = isAddition ? left + right : left - right;
            max[b][e] = calc;
            min[b][e] = calc;
            return calc;
        } else if (b > e) { // should not arrive
            throw new Exception();
        }
        
        ArrayList<Integer> calcs = new ArrayList<>();
        for (int op = b+1; op <= e-1; op+=2) {
            boolean isAddition = isPlus(arr[op]);
            calcs.add(isAddition
                ? calcMax(b, op-1, arr, min, max) + calcMax(op+1, e, arr, min, max)
                : calcMax(b, op-1, arr, min, max) - calcMin(op+1, e, arr, min, max)
              );
        }
        
        max[b][e] = calcs.get(0);
        for (int c : calcs) {
            max[b][e] = Math.max(max[b][e], c);
        }
        
        return max[b][e];
    }
    
    public int calcMin(int b, int e, String[] arr, int[][] min, int[][] max) throws Exception {
        if (min[b][e] != 0) {
            return min[b][e];
        } else if (b == e) {
            max[b][b] = Integer.parseInt(arr[b]);
            min[b][b] = Integer.parseInt(arr[b]);
            return min[b][b];
        } else if (e - b == 2) {
            int left = Integer.parseInt(arr[b]);
            boolean isAddition = isPlus(arr[b+1]);
            int right = Integer.parseInt(arr[b+2]);
            int calc = isAddition ? left + right : left - right;
            max[b][e] = calc;
            min[b][e] = calc;
            return calc;
        } else if (b > e) { // should not arrive
            throw new Exception();
        }
        
        ArrayList<Integer> calcs = new ArrayList<>();
        for (int op = b+1; op <= e-1; op+=2) {
            boolean isAddition = isPlus(arr[op]);
            calcs.add(isAddition
                ? calcMin(b, op-1, arr, min, max) + calcMin(op+1, e, arr, min, max)
                : calcMin(b, op-1, arr, min, max) - calcMax(op+1, e, arr, min, max)
              );
        }
        
        min[b][e] = calcs.get(0);
        for (int c : calcs) {
            min[b][e] = Math.min(min[b][e], c);
        }
        
        return min[b][e];
    }
}

class Coord {
    int b;
    int e;
    
    public Coord(int b, int e) {
        this.b = b;
        this.e = e;
    }
    
    @Override
    public boolean equals(Object oth) {
        Coord o = (Coord)oth;
        return b == o.b && e == o.e;
    }
    
    @Override
    public int hashCode() {
        return b * 3 + e * 2;
    }
}