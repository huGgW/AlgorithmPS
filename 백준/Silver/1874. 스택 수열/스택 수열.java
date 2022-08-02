import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int lim = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int max = 0; // max number pushed into stack
		int before = 0; // before number of sequence
		boolean available = true;
		ArrayDeque<Boolean> cmds = new ArrayDeque<>(2*lim);
		ArrayDeque<Integer> s = new ArrayDeque<>(lim);
		for (int i = 0; i < lim; i++) {
			int n = Integer.parseInt(in.readLine());
			// new max number to be added to sequence, use stack to push and pop
			if (n > max) {
				for (int e = max+1; e <= n; e++) {
					cmds.add(true);
					s.push(e);
				}
				cmds.add(false);
				s.pop();
				max = n;
			}
			// else only pop to add numbers to sequence
			else {
				if (n < before && s.pop() == n) {
					cmds.add(false);
				}
				else { // n > before is not possible
					available = false;
					break;
				}
			}
			before = n;
		}
		if (available) {
			for (boolean b : cmds) {
				out.write((b ? "+" : "-")); out.newLine();
			}
		} else {
			out.write("NO");
		}
		out.flush();
	}
}