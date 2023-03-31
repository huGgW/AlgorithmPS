class Solution {
    boolean solution(String s) {
        int cnt = 0;
        
        for (char c : s.toCharArray()) {
            switch(c) {
                case '(': {
                    cnt++;
                    break;
                }
                case ')': {
                    cnt--;
                    break;
                }
                default: {
                    return false;
                }
            }
            if (cnt < 0) {
                return false;
            }
        }

        return cnt == 0;
    }
}