import java.util.*;

class Solution {
    public int solution(String numbers) {
        HashSet<Integer> nums = possibleNums(numbers);
        
        int cnt = 0;
        for (int n : nums) {
            System.out.println(n);
            if (isPrime(n)) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public HashSet<Integer> possibleNums(String numbers) {
        HashSet<Integer> nums = new HashSet<Integer>();
        
        addingStrings("", numbers, new HashSet<Integer>(), nums, 0);
        
        return nums;
    }
    
    public void addingStrings(String s, String numbers, HashSet<Integer> included, HashSet<Integer> results, int k) {
        if (k == numbers.length()) {
            if (!s.equals("")) {
                results.add(Integer.parseInt(s));
            }
            return;
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!included.contains(i)) {
                included.add(i);
                addingStrings(s + numbers.substring(i, i+1), numbers, included, results, k+1);
                included.remove(i);
            }
        }
        
        addingStrings(s, numbers, included, results, k+1);
    }
    
    public boolean isPrime(int n) {
        if (n <= 1) { return false; }
        
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}