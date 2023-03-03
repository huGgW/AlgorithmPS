import java.util.ArrayList;
import java.io.*;

public class Main {
    static Heap heap = new Heap();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    System.in
                    )
                );
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    System.out
                    )
                );

        int N = Integer.parseInt(
                reader.readLine().trim()
                );
        
        for (int i = 0; i < N; i++) {
            int commandNum = Integer.parseInt(
                reader.readLine().trim()
                );
            executeCommand(commandNum, writer);
        }

        reader.close();
        writer.flush();
        writer.close();
    }

    static void executeCommand(long commandNum, BufferedWriter writer) throws Exception {
        if (commandNum == 0) {
            long num = heap.pop();
            writer.write(String.format("%d\n", num));
        } else {
            heap.put(commandNum);
        }
    }
}

class Heap {
    ArrayList<Long> heap;

    Heap() {
        heap = new ArrayList<Long>();
    }

    void put(long x) {
        heap.add(x);

        // Heapify
        int currIdx = heap.size() - 1;
        int parentIdx = parent(currIdx);

        while (parentIdx >= 0 && absLess(heap.get(currIdx), heap.get(parentIdx))) {
            swap(currIdx, parentIdx);
            currIdx = parentIdx;
            parentIdx = parent(currIdx);
        }
    }

    long pop() {
        if (heap.isEmpty()) {
            return 0;
        }

        // get first elem, move last elem to the first elem, delete last elem
        long firstVal = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        // Heapify
        int currIdx = 0;
        int leftIdx = left(currIdx);
        int rightIdx = right(currIdx);
        while (true) {
            int candidateIdx;
            if (leftIdx < heap.size() && rightIdx < heap.size()) {
                candidateIdx = (absLess(heap.get(leftIdx), heap.get(rightIdx)))
                    ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) {
                candidateIdx = leftIdx;
            } else if (rightIdx < heap.size()) {
                candidateIdx = rightIdx;
            } else {
                break;
            }

            if (absLess(heap.get(candidateIdx), heap.get(currIdx))) {
                swap(candidateIdx, currIdx);
                currIdx = candidateIdx;
                leftIdx = left(currIdx);
                rightIdx = right(currIdx);
            } else { 
                break;
            }
        }

        return firstVal;
    }

    private int left(int i) {
        return (i*2) + 1;
    }

    private int right(int i) {
        return (i+1) * 2;
    }

    private int parent(int i) {
        return ((i+1) / 2) - 1;
    }

    private void swap(int i, int j) {
        long tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    private boolean absLess(long x, long y) {
        long absX = Math.abs(x);
        long absY = Math.abs(y);
        if (absX == absY) {
            return x < y;
        } else {
            return absX < absY;
        }
    }
}
