import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] solution(int[] prices) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int[] answer = new int[prices.length];
        
        int i;
        for (i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int pos = stack.pop();
                answer[pos] = i - pos;
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int pos = stack.pop();
            answer[pos] = i-1 - pos;
        }
        
        return answer;
    }
}