import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int density = Integer.parseInt(in.readLine());

        // add edges & find max edge
        int[] historyDir = new int[6];
        int[] historyLen = new int[6];
        int maxIdx = 0;
        for (int i = 0; i < 6; i++) {
            String[] tmp = in.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int len = Integer.parseInt(tmp[1]);
            historyDir[i] = n;
            historyLen[i] = len;

            if (historyLen[maxIdx] < historyLen[i]) {
                maxIdx = i;
            }
        }

        // Find the begining of the wiggle
        int startWiggle = circularIdx(maxIdx+1);
        int nenext = circularIdx(startWiggle+2);
        if (historyDir[startWiggle] != historyDir[nenext]) {
            startWiggle = circularIdx(startWiggle + 1);
            nenext = circularIdx(nenext + 1);
        }

        // Calculate area
        int area = (
                        historyLen[circularIdx(startWiggle+4)] * historyLen[circularIdx(startWiggle+5)]
                    ) - (
                        historyLen[circularIdx(startWiggle+1)] * historyLen[nenext]
                    );

        System.out.println(density * area);
    }
    static int circularIdx(int n) {
        return (n % 6);
    }
}