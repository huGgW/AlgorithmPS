import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
    	HashMap<String, Integer> map = new HashMap<>();
        for (String[] sarr : clothes) {
        	map.put(sarr[1], map.getOrDefault(sarr[1], 1) + 1);
        }
        
        int answer = 1;
        for (int x : map.values()) {
        	answer *= x;
        }
        answer--;
        
        return answer;
    }
}