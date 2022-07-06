import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a;
		int b;
		a = s.nextInt();
		b = s.nextInt();
		
		int b_1 = b%10;
		int b_10 = (b/10)%10;
		int b_100 = b/100;

		int third = a * b_1;
		int fourth = a * b_10;
		int fifth = a * b_100;
		int result = third + fourth*10 + fifth*100;

		System.out.println(third + "\n" + fourth + "\n" + fifth + "\n" + result);
	}
}