import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[][] paper = makePaper(n, in);

        int[] cnt = new int[2];
        countPapers(paper, cnt, 0, n-1, 0, n-1);

        System.out.println(String.format("%d\n%d",cnt[0], cnt[1]));
    }

    static int[][] makePaper(int n, BufferedReader in) throws IOException {
        int[][] paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            paper[i] = Arrays.stream(
                in.readLine().split(" ")
            ).mapToInt(
                x -> Integer.parseInt(x)
            ).toArray();
        }
        return paper;
    }

    static void countPapers(int[][] paper, int[] cnt, int il, int ih, int jl, int jh) {
        if (il == ih && jl == jh) {
            cnt[paper[il][jl]]++;
        } else {
            int init = paper[il][jl];
            boolean isUnified = true;
            LOOP:
            for (int i = il; i <= ih; i++) {
                for (int j = jl; j <= jh; j++) {
                    isUnified = (paper[i][j] == init);
                    if (!isUnified) { break LOOP; }
                }
            }
            if (isUnified) {
                cnt[init]++;
            } else {
                int imid = (il + ih) / 2;
                int jmid = (jl + jh) / 2;
                countPapers(paper, cnt, il, imid, jl, jmid);
                countPapers(paper, cnt, il, imid, jmid+1, jh);
                countPapers(paper, cnt, imid+1, ih, jl, jmid);
                countPapers(paper, cnt, imid+1, ih, jmid+1, jh);
            }
        }
    }
}