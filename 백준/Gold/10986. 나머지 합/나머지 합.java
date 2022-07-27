import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(
			in.readLine().split(" ")
			).mapToInt(x -> Integer.parseInt(x)
			).toArray();
		
		int n = tmp[0], m = tmp[1];

		int resid[] = new int[n+1];
		tmp = Arrays.stream(
			in.readLine().split(" ")
			).mapToInt(x -> Integer.parseInt(x)
			).toArray();
		for (int i = 1; i <= n; i++) {
			resid[i] = tmp[i-1] % m;
		}

		for (int i = 1; i <= n; i++) {
			resid[i] = (resid[i-1]+ resid[i]) % m;
		}

		int[] residCnt = new int[m];
		for (int x : resid) {
			residCnt[x]++;
		}

		long cnt = 0;
		for (int x: residCnt) {
			cnt += ((long)x * (long)(x-1)) / (long)2;
		}

		System.out.print(cnt);
	}	
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}