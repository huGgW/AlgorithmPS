import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int totalTime = transToTime(s);
        System.out.println(totalTime);
    }
    static int reachTime(int n) {
        return n + 1;
    }
    static int transToTime(String s) {
        int totalTime = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit = 0;
            switch(c) {
            case 'A':
            case 'B':
            case 'C': {digit = 2; break;}
            case 'D':
            case 'E':
            case 'F': {digit = 3; break;}
            case 'G':
            case 'H':
            case 'I': {digit = 4; break;}
            case 'J':
            case 'K':
            case 'L': {digit = 5; break;}
            case 'M':
            case 'N':
            case 'O': {digit = 6; break;}
            case 'P':
            case 'Q':
            case 'R': 
            case 'S': {digit = 7; break;}
            case 'T':
            case 'U':
            case 'V': {digit = 8; break;}
            case 'W': 
            case 'X':
            case 'Y':
            case 'Z': {digit = 9; break;}
            }
            int t = reachTime(digit);
            totalTime += t;
        }
        return totalTime;
    }
}