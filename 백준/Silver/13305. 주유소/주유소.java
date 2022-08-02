import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());

		long[] loads = Arrays.stream(
			in.readLine().split(" ")
		).mapToLong(
			x -> (long)(Integer.parseInt(x))
		).toArray();

		long[] prices = Arrays.stream(
			in.readLine().split(" ")
		).mapToLong(
			x -> (long)(Integer.parseInt(x))
		).toArray();

		long min = prices[0];
		long total = 0;
		for (int i = 0; i < n-1; i++) {
			min = (prices[i] < min) ? prices[i] : min;
			total += min * loads[i];
		}

		System.out.println(total);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}