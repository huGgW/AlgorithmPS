import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a;
		int b;
		a = s.nextInt();
		b = s.nextInt();
		int sum = a + b;
		int dif = a - b;
		int mul = a * b;
		int div = a / b;
		int res = a % b;
		System.out.println(sum);
		System.out.println(dif);
		System.out.println(mul);
		System.out.println(div);
		System.out.println(res);
	}
}