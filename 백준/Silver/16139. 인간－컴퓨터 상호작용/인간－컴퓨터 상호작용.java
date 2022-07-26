import java.util.*;
import java.io.*;

public class Main {
	public static void main (String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] str = in.readLine().toCharArray();
		int n = Integer.parseInt(in.readLine());
		HashMap<Character, int[]> sums = new HashMap<>();

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			String[] tmp = in.readLine().split(" ");
			char c = tmp[0].charAt(0);
			int b = Integer.parseInt((tmp[1]));
			int e = Integer.parseInt((tmp[2]));

			int[] sumsChar = sums.get(c);
			if (sumsChar == null) {
				sumsChar = new int[str.length];
				sumsChar[0] = (str[0] == c ? 1 : 0);
				for (int j = 1; j < sumsChar.length; j++) {
					sumsChar[j] = sumsChar[j-1] + (str[j] == c ? 1 : 0);
				}
				sums.put(c, sumsChar);
			}
			out.write(Integer.toString(
				sumsChar[e] - (b >= 1 ? sumsChar[b-1] : 0)));
			out.newLine();
		}

		out.flush();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}