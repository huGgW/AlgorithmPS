import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());

		Meeting[] schedules = new Meeting[n];
		for (int i = 0; i < n; i++) {
			int[] tmp = Arrays.stream(
				in.readLine().split(" ")
			).mapToInt(x -> Integer.parseInt(x)
			).toArray();
			schedules[i] = new Meeting(tmp[0], tmp[1]);
		}

		// sort
		Arrays.sort(schedules);

		int cnt = 1;
		for (int i = 0; i < n; ) {
			int j = i + 1;
			for (; j < n; j++) {
				if (schedules[j].start >= schedules[i].end) {
					cnt++;
					break;
				}
			}
			i = j;
		}

		System.out.print(cnt);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}

class Meeting implements Comparable<Meeting> {
	public int start;
	public int end;

	public Meeting(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public int compareTo(Meeting other) {
		if (Integer.valueOf(this.end).compareTo(other.end) == 0) {
			return Integer.valueOf(this.start).compareTo(other.start);
		}
		else {
			return Integer.valueOf(this.end).compareTo(other.end);
		}
	}
}