import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            // Initialize
            long[] tmp = toLongArrFromStrArr(in.readLine().split(" "));
            int n = (int)tmp[0];
            // break when input is 0
            if (tmp[0] == 0 && tmp.length == 1) { break; }
            // array for histogram, 
            long[] hist = new long[n];
            // fill hist, segment tree storing min idx
            for (int i = 0; i < n; i++) { 
                hist[i] = tmp[i+1]; 
            }
            SegmentMinTree st = new SegmentMinTree(hist);
            // find maximum Rectangle area, and print
            long maxArea = maxRectArea(0, n-1, st);
            out.write(Long.toString(maxArea)); out.newLine();
        }

        // flush
        out.flush();
    }

    static long[] toLongArrFromStrArr(String[] s) {
        return Arrays.stream(s).mapToLong(x -> Long.parseLong(x)).toArray();
    }

    static long maxRectArea(int b, int e, SegmentMinTree st) {
        // base
        if (b > e) { return 0; }
        else if (b == e) { return st.data[b]; }
        // recursive (divide)
        else {
            // find first occurance position of minimum height
            int minIdx = st.getMinIdx(b, e);
            // split at the first occurance position of minimum height,
            // and get the max Rectangle area of splitted ones,
            // and compare with unsplitted widest max Rectangle area,
            // and return max one.
            long result = Arrays.stream(
                    new long[]{
                        (long)(e - b + 1) * st.data[minIdx],
                        maxRectArea(b, minIdx-1, st),
                        maxRectArea(minIdx+1, e, st)
                    }
                ).max().getAsLong();
            return result;
        }
    }

}

class SegmentMinTree {
    int[] minIdxs;
    long[] data;
    final static long MAX = 1000000000;

    public SegmentMinTree(long[] d) {
        this.data = d;
        int n = d.length;
        // consider the tree to get the size
        int h = (int)Math.pow(2, (Math.ceil(Math.log10(n) / Math.log10(2)) + 1)) - 1;
        minIdxs = new int[h];
        // make segment tree
        initialize(0, n-1, 0);
    }

    private int toLeft(int pos) {
        return (2 * pos) + 1;
    }

    private int toRight(int pos) {
        return (2 * pos) + 2;
    }

    public long getHeight(int idx) {
        if (idx == -1) { return  MAX; }
        else { return data[idx]; }
    }

    private void initialize (int from, int to, int pos) {
        // base case
        if (from == to) {
            minIdxs[pos] = from;
        } else {
            int mid = (from + to) / 2;
            int leftPos = toLeft(pos);
            int rightPos = toRight(pos);
            initialize(from, mid, leftPos);
            initialize(mid+1, to, rightPos);
            minIdxs[pos] = (data[minIdxs[leftPos]] <= data[minIdxs[rightPos]]) ? minIdxs[leftPos] : minIdxs[rightPos];
        }
    }

    public int getMinIdx(int from, int to) {
        return getMinIdxRec(from, to, 0, data.length-1, 0);
    }
    // Assume always range of what we find is inside bottom and top
    private int getMinIdxRec(int from, int to, int bottom, int top, int pos) {
        // 1. out of bottom and top
        if (to < bottom || from > top) {
            return -1;
        } // 2. range we find wraps the given portion
        else if (from <= bottom && to >= top) {
            return minIdxs[pos];
        } // 3. it is intersected, or given portion is wraps the range we find
        else {
            int mid = (bottom + top) / 2;
            int leftMinIdx = getMinIdxRec(from, to, bottom, mid, toLeft(pos));
            int rightMinIdx = getMinIdxRec(from, to, mid+1, top, toRight(pos));
            if  (leftMinIdx == -1) { return rightMinIdx; }
            else if (rightMinIdx == -1) { return leftMinIdx; }
            else { return (data[leftMinIdx] <= data[rightMinIdx]) ? leftMinIdx : rightMinIdx; }
        }
    }
}

