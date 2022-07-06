import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a;
		int b;
		a = s.nextInt();
		b = s.nextInt();
		double div = (double)a / (double)b;
		System.out.println(div);
	}
}