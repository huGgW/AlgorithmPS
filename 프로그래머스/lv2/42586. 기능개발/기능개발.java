import java.util.LinkedList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] needDates = new int[speeds.length];
        for (int i = 0; i < needDates.length; i++) {
            needDates[i] = ((99 - progresses[i]) / speeds[i]) + 1;
            // System.out.print(needDates[i]);
            // System.out.print(" ");
        }
        
        LinkedList<Integer> answerList = new LinkedList<>();
        int before = needDates[0];
        int cnt = 0;
        for (int i = 0; i < needDates.length; i++) {
            if (needDates[i] > before) {
                answerList.add(cnt);
                cnt = 1;
                before = needDates[i];
            } else {
                cnt++;
            }
            
        }
        answerList.add(cnt);
        
        return answerList.stream().mapToInt((x) -> x).toArray();
    }
}