import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Deque d = new Deque();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String[] tmp = in.readLine().split(" ");
            if (tmp.length == 2) {
                switch (tmp[0]) {
                    case "push_front": {
                        d.push_front(Integer.parseInt(tmp[1]));
                        break;
                    }
                    case "push_back": {
                        d.push_back(Integer.parseInt(tmp[1]));
                        break;
                    }
                }
            }
            else {
                switch (tmp[0]) {
                    case "pop_front": {
                        out.write(
                            Integer.toString(
                                d.pop_front()
                            )
                        );
                        out.newLine();
                        break;
                    }
                    case "pop_back": {
                        out.write(
                            Integer.toString(
                                d.pop_back()
                            )
                        );
                        out.newLine();
                        break;
                    }
                    case "size": {
                        out.write(
                            Integer.toString(
                                d.size                            )
                        );
                        out.newLine();
                        break;
                    }
                    case "empty": {
                        out.write(
                            Integer.toString(
                                d.empty()
                            )
                        );
                        out.newLine();
                        break;
                    }
                    case "front": {
                        out.write(
                            Integer.toString(
                                d.front()
                            )
                        );
                        out.newLine();
                        break;
                    }
                    case "back": {
                        out.write(
                            Integer.toString(
                                d.back()
                            )
                        );
                        out.newLine();
                        break;
                    }
                }
            }
        }
        out.flush();
    }
}

class Deque {
    class Node {
        int data;
        Node before = null;
        Node after = null;

        public Node(int x) {
            data = x;
        }
    }
    int size = 0;
    Node head = null;
    Node tail = null;

    void push_front(int x) {
        Node tmp = new Node(x);
        tmp.after = this.tail;
        if (this.tail != null) { this.tail.before = tmp; }
        this.tail = tmp;
        if (size == 0) { this.head = tmp; }
        size++;
    }
    void push_back(int x) {
        Node tmp = new Node(x);
        tmp.before = this.head;
        if (this.head != null) { this.head.after = tmp; }
        this.head = tmp;
        if (size == 0) { this.tail = tmp; }
        size++;
    }
    int pop_front() {
        if (size <= 0) { return -1; }
        int x = tail.data;
        if (size == 1) { head = null; tail = null; }
        else {
            tail = tail.after;
            tail.before = null;
        }
        size--;
        return x;
    }
    int pop_back() {
        if (size <= 0) { return -1; }
        int x = head.data;
        if (size == 1) { head = null; tail = null; }
        else {
            head = head.before;
            head.after = null;
        }
        size--;
        return x;
    }
    int empty() {
        return size == 0 ? 1 : 0;
    }
    int front() {
        if (size == 0) { return -1; }
        return tail.data;
    }
    int back() {
        if (size == 0) { return -1; }
        return head.data;
    }
}