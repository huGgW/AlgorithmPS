import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int min = sc.nextInt();
        int time = sc.nextInt();
        int temp = min + time;
        hour = ((temp / 60) + hour) % 24;
        min = temp % 60;
        System.out.print(hour + " " + min);
    }
}