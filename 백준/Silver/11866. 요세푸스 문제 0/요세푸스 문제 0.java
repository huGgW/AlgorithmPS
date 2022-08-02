import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		ArrayDeque<Integer> queue = new ArrayDeque<>(n);

		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}

		System.out.print("<");
		while (queue.size() > 1) {
			for (int i = 0; i < k-1; i++) {
				queue.add(queue.remove());
			}
			System.out.print(queue.remove() + ", ");
		}
		System.out.print(queue.remove() + ">");
	}
}