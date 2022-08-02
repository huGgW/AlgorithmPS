import java.io.*;

public class Main {
	public static void main(String[] args) {
	try {
		Stack<Integer> s = new Stack<>();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());

		for (int i = 0; i < n; i++) {
			String[] cmd = in.readLine().split(" ");
			if (cmd.length == 2) {
				int data = Integer.parseInt(cmd[1]);
				s.push(data);
			}
			else {
				switch (cmd[0]) {
				case "top": {
					Integer data = s.top();
					out.write(Integer.toString((data != null) ? data : -1) + "\n");
					break;
					}
				case "pop": {
					Integer data = s.pop();
					out.write(Integer.toString((data != null) ? data : -1) + "\n");
					break;
					}
				case "size": {
					out.write(Integer.toString(s.getSize()) + "\n");
					break;
					}
				case "empty": {
					out.write(Integer.toString(s.isEmpty() ? 1 : 0) + "\n");
					break;
					}
				}
			}
		}
		out.flush();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
}

class Stack<T> {
	class LinkedList<S> {
		S data;
		LinkedList<S> next;

		public LinkedList(S data) {
			this.data = data;
		}
	}

	int size = 0;
	LinkedList<T> tail = null;

	void push(T data) {
		size++;
		LinkedList<T> toPush = new LinkedList<>(data);
		toPush.next = tail;
		tail = toPush;
	}

	T pop() {
		if (size == 0) {
			return null;
		}
		size--;
		T data = tail.data;
		tail = tail.next;
		return data;
	}

	T top() {
		if (tail != null) {
			return tail.data;
		}
		else {
			return null;
		}
	}

	int getSize() {
		return this.size;
	}

	boolean isEmpty() {
		return this.size == 0;
	}
}