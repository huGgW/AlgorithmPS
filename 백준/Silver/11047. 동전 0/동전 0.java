import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(
			in.readLine().split(" ")
		).mapToInt(
			x -> Integer.parseInt(x)
		).toArray();

		int n = tmp[0], price = tmp[1];

		int[] coins = new int[n];
		for (int i = n-1; i >= 0; i--) { // descending order
			coins[i] = Integer.parseInt(in.readLine());
		}

		int cnt = 0;
		for (int c : coins){
			cnt += (price / c);
			price %= c;
			if (price == 0) break;
		}

		System.out.print(cnt);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}