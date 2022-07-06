import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        char[][] shape = new char[n][n];
        fillShape(shape, n);
        printShape(shape);
    }

    static void fillShape(char[][] shape, int n) {
        // Fill the upper left corner
        if (n == 1) {
            shape[0][0] = '*';
            return;
        }
        else if (n > 1) {
            fillShape(shape, n/3);
        }

        int unit = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Copy the block of the corner to others
                if (i != 0 || j != 0) {
                    for (int bi = 0; bi < unit; bi++) {
                        for (int bj = 0; bj < unit; bj++) {
                            // Empty when middle
                            if (i == 1 && j == 1) {
                                shape[(i*unit)+bi][(j*unit)+bj] = ' ';
                            }
                            // Copy the block
                            else {
                                shape[(i*unit)+bi][(j*unit)+bj] = shape[bi][bj];
                            }
                        }
                    }
                }
            }
        }
    }

    static void printShape(char[][] shape) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 0; i < shape.length; i++) {
                out.write(shape[i]);
                out.newLine();
            }
            out.flush();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}