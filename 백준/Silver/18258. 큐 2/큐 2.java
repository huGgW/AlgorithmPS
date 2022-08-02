import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Queue q = new Queue();
		int n = Integer.parseInt(in.readLine());

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			String[] cmd = in.readLine().split(" ");
			if (cmd.length == 2) {
				q.push(Integer.parseInt(cmd[1]));
			}
			else { switch (cmd[0]) {
			case "front": {
				out.write(Integer.toString(q.front())); out.newLine();
				break;
			}
			case "back": {
				out.write(Integer.toString(q.back())); out.newLine();
				break;
			}
			case "size": {
				out.write(Integer.toString(q.size)); out.newLine();
				break;
			}
			case "empty": {
				out.write(Integer.toString(q.empty())); out.newLine();
				break;
			}
			case "pop": {
				out.write(Integer.toString(q.pop())); out.newLine();
				break;
			}
			}}
		}

		out.flush();
	}
}

class Queue {
	class Node {
		int data;
		Node next = null;

		public Node(int d) {
			data = d;
		}
	}

	int size = 0;
	Node head = null;
	Node tail = null;

	public void push(int x) {
		size++;
		Node tmp = new Node(x);
		if (tail != null) {
			tail.next = tmp;
		} else {
			head = tmp;
		}
		tail = tmp;
	}

	public int pop() {
		if (size == 0) {
			return -1;
		}
		size--;
		int data = head.data;
		head = head.next;
		if (size == 0) {
			tail = null;
		}
		return data;
	}

	public int empty() {
		return (size == 0) ? 1 : 0;
	}

	public int front() {
		if (size == 0) {
			return -1;
		} else {
			return head.data;
		}
	}

	public int back() {
		if (size == 0) {
			return -1;
		} else {
			return tail.data;
		}
	}
}