class Solution {
    public int[] solution(int brown, int yellow) {
        int x;
        for (x = 1; ; x++) {
            if (yellow % x != 0) {
                continue;
            }
            int y = yellow / x;
            int calcBrown = 2 * (x + y + 2);
            
            if (brown == calcBrown) {
                break;
            }
        }
        
        int[] answer = {yellow / x + 2, x + 2};
        return answer;
    }
}

/*
yellow : x * y
brown : 2 * (x + y + 2)
*/