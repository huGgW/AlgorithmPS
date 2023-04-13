class Solution {
    public int solution(int[][] sizes) {
        for (int[] card : sizes) {
            bigOnLeft(card);
        }
        
        int leftMax = 0;
        int rightMax = 0;
        for (int[] card : sizes) {
            leftMax = Math.max(leftMax, card[0]);
            rightMax = Math.max(rightMax, card[1]);
        }
        
        int answer = leftMax * rightMax;
        return answer;
    }
    
    public void bigOnLeft(int[] card) {
        if (card[0] < card[1]) {
            int tmp = card[1];
            card[1] = card[0];
            card[0] = tmp;
        }
    }
}