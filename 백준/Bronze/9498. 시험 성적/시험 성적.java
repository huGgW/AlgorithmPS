import java.util.*;
public class Main {
    //시험 점수를 입력받아 90 ~ 100점은 A, 80 ~ 89점은 B, 70 ~ 79점은 C, 60 ~ 69점은 D, 나머지 점수는 F를 출력하는 프로그램을 작성하시오.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
    int point;
    point = in.nextInt();
    if (point >= 90 && 100 >= point) {
        System.out.print('A');
    }
    else if (point >= 80 && 90 > point) {
        System.out.print('B');
    }
    else if (point >= 70 && 80 > point) {
        System.out.print('C');
    }
    else if (point >= 60 && 70 > point) {
        System.out.print('D');
    }
    else {
        System.out.print('F');
    }
    }
}