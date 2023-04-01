import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> maxs = new PriorityQueue<>((a, b) -> (b-a));
        Deque<Integer> idxQueue = new LinkedList<Integer>();
        
        for (int i = 0; i < priorities.length; i++) {
            maxs.offer(priorities[i]);
            idxQueue.offer(i);
        }
    
    	int cnt = 0;
    	int idx = -1;
    	while (idx != location) {
            idx = idxQueue.poll();
            if (priorities[idx] == maxs.peek()) {
                maxs.poll();
                cnt++;
            } else if (priorities[idx] < maxs.peek()) {
                idxQueue.offer(idx);
                idx = -1;
            }
        }
    
        return cnt;
    }
}