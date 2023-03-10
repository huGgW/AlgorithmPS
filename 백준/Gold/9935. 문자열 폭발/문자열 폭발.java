import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        System.in
                        )
                );

        String str = reader.readLine().trim();
        String bomb = reader.readLine().trim();
        reader.close();

        char[] strArr = str.toCharArray();
        char[] bombArr = bomb.toCharArray();

        // Setup stack
        Deque<Character> inputStack = new LinkedList<Character>();
        Deque<Character> outputStack = new LinkedList<Character>();
        for (int i = 0; i < strArr.length; i++) {
            inputStack.push(strArr[i]);
        }

        // Remove Bombs while move to outputstack
        int explodeTic = bombArr.length;
        int tic = 0;
        while (!inputStack.isEmpty()) {
            char c = inputStack.pop();

            // Increase tic if character equals, 
            // else reset tic (check for first char of bomb again)
            if (c == bombArr[explodeTic - tic - 1]) {
                tic++;
            } else if (c == bombArr[explodeTic - 1]) {
                tic = 1;
            } else {
                tic = 0;
            }

            // If exploded, remove bomb and revert bombArr.length-1 chars
            // Else push char to output Stack
            if (tic == explodeTic) {
                for (int j = 0; j < explodeTic-1; j++) {
                    outputStack.pop();
                }
                for (int j = 0; j < explodeTic-1; j++) {
                    if (!outputStack.isEmpty()) {
                        inputStack.push(outputStack.pop());
                    }
                }
                tic = 0;
            } else {
                outputStack.push(c);
            }
        }

        // Write result
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        System.out
                        )
                );
        if (outputStack.isEmpty()) {
            writer.write("FRULA");
        } else {
            while (!outputStack.isEmpty()) {
                writer.write(outputStack.pop());
            }
        }
        writer.flush();
        writer.close();
    }
}