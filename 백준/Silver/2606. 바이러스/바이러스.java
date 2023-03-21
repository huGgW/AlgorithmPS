import java.io.*;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    static int cnt = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    System.in
                )
            );

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<Integer>();
        }
        boolean[] visited = new boolean[n];

        for (int i = 0; i < k; i++) {
            int[] tmp = Arrays.stream(
                    reader.readLine().split(" ")
                ).mapToInt(
                    (s) -> { return Integer.parseInt(s); }
                ).toArray();
            graph[tmp[0]-1].add(tmp[1]-1);
            graph[tmp[1]-1].add(tmp[0]-1);
        }

        dfs(0, graph, visited);

        System.out.println(cnt);
    }

    static void dfs(int p, LinkedList<Integer>[] graph, boolean[] visited) {
        if (visited[p]) { return; }

        visited[p] = true;
        cnt++;

        for (int c : graph[p]) {
            dfs(c, graph, visited);
        }
    }
}