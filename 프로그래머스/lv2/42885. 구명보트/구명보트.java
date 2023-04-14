import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int cnt = 0;
        int max = people.length;
        int min = -1;
        while (max - min > 1) {
            int weight = people[--max];
            if (people[min+1] + weight <= limit) {
                min++;
            }
            cnt++;
        }
        
        return cnt;
    }
}