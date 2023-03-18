import java.io.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    System.in
                    )
                );

        int[] tmp = Arrays.stream(
                reader.readLine().split(" ")
                ).mapToInt(
                    (s) -> { return Integer.parseInt(s); }
                ).toArray();
        int n = tmp[0];
        int m = tmp[1];
        int r = tmp[2];

        int[] visited = new int[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        

        for (int i = 0; i < m; i++) {
            tmp = Arrays.stream(
                    reader.readLine().split(" ")
                ).mapToInt(
                    (s) -> { return Integer.parseInt(s); }
                ).toArray();
            graph[tmp[0]-1].add(tmp[1]-1);
            graph[tmp[1]-1].add(tmp[0]-1);
        }
        reader.close();

        for (int i = 0; i < n; i++) {
            graph[i].sort(null);
        }

        bfs(r-1, graph, visited);

        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                System.out
            )
        );

        for (int i = 0; i < n; i++) {
            writer.write(String.valueOf(visited[i]));
            writer.write("\n");
        }

        writer.flush();
        writer.close();
    }

    static void bfs(int start, ArrayList<Integer>[] graph, int[] visited) {
        Queue<Integer> queue = new LinkedList<Integer>();
        
        int cnt = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int p = queue.poll();
            if (visited[p] != 0) { continue; }
            visited[p] = ++cnt;
            for (int c : graph[p]) {
                queue.offer(c);
            }
        }
    }
}