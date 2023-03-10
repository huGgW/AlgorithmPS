import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] nums = Arrays.stream(
			in.readLine().split(" ")
		).mapToInt(
			x -> Integer.parseInt(x)
		).toArray();

		ArrayDeque<Integer> waitingIdx = new ArrayDeque<>();
		int[] nge = new int[n];
		Arrays.fill(nge, -1);

		for (int i = 0; i < n; i++) {
			while (!waitingIdx.isEmpty() && nums[waitingIdx.peek()] < nums[i]) {
				nge[waitingIdx.pop()] = nums[i];
			}
			waitingIdx.push(i);
		}

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int x : nge) {
			out.write(Integer.toString(x) + " ");
		}
		out.flush();
	}
}