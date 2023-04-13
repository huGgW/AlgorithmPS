import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        
        if (citations[0] > n) {
            return n;
        } 
        
        for (int i = n-1; i >= 0; i--) {
            if (n-i >= citations[i]) {
                return Math.max(citations[i], n-i-1);
            }
        }
        
        return 0;
    }
}