class Solution {
    public int solution(String word) {
        int answer = 0;
        for (int i = 0; i < word.length(); i++) {
            answer += calculate(word.charAt(i), i);
        }
        return answer;
    }
    
    public int calculate(char c, int i) {
        int x = charToNum(c);
        int mult = 1;
        for (int m = 0; m < 4-i; m++) {
            mult = 1 + mult * 5;
        }
        
        return 1 + x * mult;
    }
    
    public int charToNum(char c) {
        int x = -1;
        switch(c) {
            case 'A':
                x = 0;
                break;
            case 'E':
                x = 1;
                break;
            case 'I':
                x = 2;
                break;
            case 'O':
                x = 3;
                break;
            case 'U':
                x = 4;
                break;
        }
        return x;
    }
}