import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] line = Arrays.stream(
			in.readLine().split(" ")
		).mapToInt(
			x -> Integer.parseInt(x)
		).toArray();
		Arrays.sort(line);

		int time = 0;
		for (int i = 0; i < n; i++) {
			time += ((n)-i) * line[i];
		}

		System.out.println(time);
	}
}