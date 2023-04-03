import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int x : scoville) { heap.offer(x); }
        
        int answer = 0;
        
        while (heap.peek() < K) {
            if (heap.size() <= 1) {
                return -1;
            }
            
            int min = heap.poll();
            int min2 = heap.poll();
            int hotter = min + (2 * min2);
            heap.offer(hotter);
            answer++;
        }
        
        return answer;
    }
}