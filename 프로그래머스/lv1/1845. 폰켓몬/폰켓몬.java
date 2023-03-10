import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> kind = new HashSet<>();
        for (int phonemon: nums) {
            kind.add(phonemon);
        }
        
        int totalkinds = kind.size();
        return (totalkinds > (nums.length / 2))
            ? nums.length / 2
            : totalkinds;
    }
}