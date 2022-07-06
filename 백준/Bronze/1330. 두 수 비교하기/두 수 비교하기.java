import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a;
		int b;
		a = s.nextInt();
		b = s.nextInt();
		
		if (a > b) {
			System.out.println(">");
		}
		else if (a < b) {
			System.out.println("<");
		}
		else {
			System.out.println("==");
		}
	}
}