import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> completionMap = new HashMap<>();
        for (String com : completion) {
            completionMap.put(
                com, completionMap.getOrDefault(com, 0) + 1
            );
        }
        
        for (String part : participant) {
            int count = completionMap.getOrDefault(part, 0);
            if (count == 0) {
                return part;
            }
            completionMap.put(part, count-1);
        }
        
        return "SHOULD_NOT_RETURNED";
    }
}