import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		Stack<Integer> s = new Stack<>();

		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(in.readLine());
			if (x == 0) {
				s.pop();
			}
			else {
				s.push(x);
			}
		}

		System.out.println(s.stream().mapToInt(x -> x).sum());
	}
}