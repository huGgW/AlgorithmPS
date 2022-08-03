import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(
			in.readLine().split(" ")
		).mapToInt(x -> Integer.parseInt(x)
		).toArray();
		int n = tmp[0];
		int m = tmp[1];

		int[] eliminate = Arrays.stream(
			in.readLine().split(" ")
		).mapToInt(x -> Integer.parseInt(x)
		).toArray();

		ArrayDeque<Integer> growDeque = new ArrayDeque<>(n);
		for (int i = 1; i <= n; i++) { growDeque.add(i); }
		ArrayDeque<Integer> shrinkDeque = growDeque.clone();

		int cnt = 0;
		for (int x : eliminate) {
			int growCnt = 0, shrinkCnt = 0;
			while (growDeque.getFirst() != x) {
				grow(growDeque);
				growCnt++;
			}
			growDeque.removeFirst();
			while (shrinkDeque.getFirst() != x) {
				shrink(shrinkDeque);
				shrinkCnt++;
			}
			shrinkDeque.removeFirst();

			if (growCnt <= shrinkCnt) {
				cnt += growCnt;
				shrinkDeque = growDeque.clone();
			} else {
				cnt += shrinkCnt;
				growDeque = shrinkDeque.clone();
			}
		}

		System.out.println(cnt);
	}

	static void shrink(ArrayDeque<Integer> d) {
		d.addFirst(d.removeLast());
	}
	static void grow(ArrayDeque<Integer> d) {
		d.addLast(d.removeFirst());
	}
}