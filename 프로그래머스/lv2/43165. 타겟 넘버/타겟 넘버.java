import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        Deque<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        
        for (int x : numbers) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int r = queue.poll();
                queue.offer(r + x);
                queue.offer(r - x);
            }
        }
        
        int answer = 0;
        for (int r : queue) {
            if (r == target) {
                answer++;
            }
        }
        
        return answer;
    }
}