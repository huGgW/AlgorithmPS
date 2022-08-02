import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(in.readLine());
		// Stack<Integer> s = new Stack<>();
		
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			for (char c : in.readLine().toCharArray()) {
				if (c == '(') {
					cnt = (cnt >= 0) ? cnt+1 : cnt;
				} else if (c == ')') {
					cnt--;
				}
			}
			out.write((cnt == 0) ? "YES\n" : "NO\n");
		}
		out.flush();
	}
}