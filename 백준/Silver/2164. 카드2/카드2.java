import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		ArrayDeque<Integer> queue = new ArrayDeque<>(n);

		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}

		while (queue.size() > 1) {
			queue.remove();
			queue.add(queue.remove());
		}
		System.out.println(queue.peekLast());
	}
}