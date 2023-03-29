import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int before = -1;
        LinkedList<Integer> answerList = new LinkedList<>();
        for (int x : arr) {
            if (x == before) {
                continue;
            }
            answerList.add(x);
            before = x;
        }
        
        int[] answer = answerList
            .stream()
            .mapToInt(
                (i) -> (i)
            ).toArray();
        return answer;
    }
}