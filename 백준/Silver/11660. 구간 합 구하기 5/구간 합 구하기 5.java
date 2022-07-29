import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(in.readLine().split(" "))
			.mapToInt(x -> Integer.parseInt(x))
			.toArray();
		int n = tmp[0], m = tmp[1];

		int[][] table = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			tmp = Arrays.stream(
				in.readLine().split(" ")
			).mapToInt(
				s -> Integer.parseInt(s)
			).toArray();
			for (int j = 1; j <= n; j++) {
				table[i][j] = tmp[j-1];
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				table[i][j] += table[i][j-1] + table[i-1][j] - table[i-1][j-1];
			}
		}

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < m; i++) {
			tmp = Arrays.stream(
				in.readLine().split(" ")
			).mapToInt(
			s -> Integer.parseInt(s)
			).toArray();

			int x1 = tmp[0], y1 = tmp[1], x2 = tmp[2], y2 = tmp[3];
			int sum = table[x2][y2]
				- table[x2][y1-1] - table[x1-1][y2]
				+ table[x1-1][y1-1];

			out.write(Integer.toString(sum)); out.newLine();
		}
		out.flush();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}