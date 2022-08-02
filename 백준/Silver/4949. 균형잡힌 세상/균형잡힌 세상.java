import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<Character> s = new Stack<>();
		
		while (true) {
			s.clear();
			String sentence = in.readLine();
			if (sentence.equals(".")) { break; }
			
			INNERLOOP:
			for (char c : sentence.toCharArray()) {
				switch (c) {
					case '(': {
						s.push(c);
						break;
					}
					case '[': {
						s.push(c);
						break;
					}
					case ')': {
						if (s.isEmpty() || s.peek() != '(') {
							s.push('!');
							break INNERLOOP;
						}
						else {
							s.pop();
							break;
						}
					}
					case ']': {
						if (s.isEmpty() || s.peek() != '[') {
							s.push('!');
							break INNERLOOP;
						}
						else {
							s.pop();
							break;
						}
					}
					default: {
						break;
					}
				}
			}
			out.write((s.isEmpty()) ? "yes\n" : "no\n");
		}
		out.flush();
	}
}