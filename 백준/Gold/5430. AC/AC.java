import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());

		for (int i_ = 0; i_ < n; i_++) {
			// Commands
			String cmd = in.readLine();
			// List length
			int len = Integer.parseInt(in.readLine());
			// List
			Liist liist = new Liist(in.readLine(), len);

			try {
				int[] result = liist.execute(cmd);
				out.write("[");
				if (result.length != 0) {
					for (int i = 0; i < result.length-1; i++) {
						out.write(Integer.toString(result[i]) + ",");
					}
					out.write(Integer.toString(result[result.length-1]));
				}
				out.write("]");
			} catch (Exception e) {
				out.write("error");
			}
			out.newLine();
		}
		
		out.flush();
	}
}
class Liist {
	ArrayDeque<Integer> storage = new ArrayDeque<>();
	boolean reversed = false;

	public Liist(String instr, int len) {
		if (len > 0) {
			instr = instr.substring(1, instr.length()-1);
			for (String s : instr.split(",")) {
				storage.add(Integer.parseInt(s));
			}
		}
	}

	public void reverse() { reversed = !reversed; }

	public void delete() throws NoSuchElementException {
		if (reversed) { storage.removeLast(); } else { storage.removeFirst(); }
	}

	public int[] execute(String cmd) throws Exception {
		for (char c : cmd.toCharArray()) {
			switch (c) {
				case 'D': {
					delete();
					break;
				}
				case 'R': {
					reverse();
					break;
				}
				default: {
					throw new Exception();
				}
			}
		}

		int n = storage.size();
		int[] result = new int[n];
		if (!reversed) {
			for (int i = 0; i < n; i++) {
				result[i] = storage.removeFirst();
			}
		} else {
			for (int i = 0; i < n; i++) {
				result[i] = storage.removeLast();
			}
		}
		return result;
	}
}