import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        Graph g = new Graph(n);
        for (int[] edge : results) {
            g.add(edge[0]-1, edge[1]-1);
        }
        
        boolean[][] clarified = new boolean[n][n];
        
        for (int x = 0; x < n; x++) {
            boolean[] visited = new boolean[n];
            Deque<Integer> queue = new LinkedList<Integer>();
            queue.offer(x);
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (visited[curr]) {
                    continue;
                }
                visited[curr] = true;
                clarified[x][curr] = true;
                clarified[curr][x] = true;
                
                for (int next : g.neighbors(curr)) {
                    if (!visited[next]) {
                        queue.offer(next);
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (countTrue(clarified[i]) == n) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public int countTrue(boolean[] arr) {
        int n = 0;
        for (boolean b : arr) {
            n = b ? n+1 : n;
        }
        return n;
    }
}

class Graph {
    int nodes;
    LinkedList<Integer>[] edges;
    
    public Graph(int n) {
        nodes = n;
        edges = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }
    }
    
    public void add(int from, int to) {
        edges[from].add(to);
    }
    
    public LinkedList<Integer> neighbors(int node) {
        return edges[node];
    }
}