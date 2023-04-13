import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answers) {
        int[] looser1 = new int[] {1, 2, 3, 4, 5};
        int[] looser2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] looser3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] scores = new int[3];
        scores[0] = countAnswer(looser1, answers);
        scores[1] = countAnswer(looser2, answers);
        scores[2] = countAnswer(looser3, answers);
        
        ArrayList<Integer> answer = new ArrayList<>(3);
        int maxAns = 0;
        for (int i = 0; i < 3; i++) {
            if (scores[i] > maxAns) {
                answer.clear();
                answer.add(i+1);
                maxAns = scores[i];
            } else if (scores[i] == maxAns) {
                answer.add(i+1);
            }
        }
        
        return answer.stream().mapToInt((x) -> x).toArray();
    }
    
    public int countAnswer(int[] looser, int[] answers) {
        int cnt = 0;
        for (int i = 0; i < answers.length; i++) {
            int j = i % looser.length;
            if (answers[i] == looser[j]) { cnt++; }
        }
        return cnt;
    }
}