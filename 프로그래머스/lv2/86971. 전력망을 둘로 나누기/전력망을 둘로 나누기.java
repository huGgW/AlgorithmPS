import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] wire : wires) {
            connect(graph, wire);
        }
        
        int minDiff = n;
        for (int[] wire : wires) {
            disconnect(graph, wire);
            int diff = traverse(graph);
            minDiff = Math.min(minDiff, diff);
            connect(graph, wire);
        }
        
        return minDiff;
    }
    
    public void connect(LinkedList<Integer>[] graph, int[] wire) {
        graph[wire[0]-1].add(wire[1]-1);
        graph[wire[1]-1].add(wire[0]-1);
    }
    
    public void disconnect(LinkedList<Integer>[] graph, int[] wire) {
        int i = graph[wire[0]-1].indexOf(wire[1]-1);
        graph[wire[0]-1].remove(i);
        int j = graph[wire[1]-1].indexOf(wire[0]-1);
        graph[wire[1]-1].remove(j);
    }
    
    public int traverse(LinkedList<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int[] cnts = new int[2];
        
        Deque<Integer> queue = new LinkedList<>();
        
        queue.offer(0);
        while(!queue.isEmpty()) {
            int x = queue.poll();
            if (!visited[x]) {
                cnts[0]++;
                visited[x] = true;
            }
            
            for (int y : graph[x]) {
                if (!visited[y]) {
                    queue.offer(y);
                }
            }
        }
        
        int next = 0;
        for (; visited[next]; next++) {}
        
        queue.clear();
        queue.offer(next);
        while(!queue.isEmpty()) {
            int x = queue.poll();
            if (!visited[x]) {
                cnts[1]++;
                visited[x] = true;
            }
            
            for (int y : graph[x]) {
                if (!visited[y]) {
                    queue.offer(y);
                }
            }
        }
        
        return Math.abs(cnts[0] - cnts[1]);
    }
}