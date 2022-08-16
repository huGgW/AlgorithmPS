import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Initialize matrix A
        Matrix A = Matrix.initializeSize(toIntArr(in.readLine().split(" ")));
        for (int i = 0; i < A.rows; i++) {
            A.fillRow(i, toIntArr(in.readLine().split(" ")));
        }

        // Initialize matrix B
        Matrix B = Matrix.initializeSize(toIntArr(in.readLine().split(" ")));
        for (int i = 0; i < B.rows; i++) {
            B.fillRow(i, toIntArr(in.readLine().split(" ")));
        }

        // Multiply and print
        System.out.print(Matrix.mult(A, B));
    }

    static int[] toIntArr(String[] arr) {
        return Arrays.stream(
                arr
            ).mapToInt(
                x -> Integer.parseInt(x)
            ).toArray();
    }
}

class Matrix {
    int rows;
    int cols;
    int[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new int[rows][cols];
    }

    public Matrix (int[][] data) {
        data = this.data;
        rows = data.length;
        cols = data[0].length;
    }

    @Override
    public String toString() {
        String s = "";
        for (int[] row : data) {
            for (int x : row) {
                s += Integer.toString(x) + " ";
            }
            s += "\n";
        }
        return s;
    }

    public int get(int r, int c) { return data[r][c]; }
    public void set(int r, int c, int x) { data[r][c] = x; }

    public static Matrix initializeSize(int[] size) {
        return new Matrix(size[0], size[1]);
    }

    public void fillRow(int r, int[] rowData) {
        data[r] = rowData;
    }

    public static Matrix mult(Matrix A, Matrix B) throws Exception {
        if (A.cols != B.rows) { throw (new Exception()); }
        Matrix resultMat = new Matrix(A.rows, B.cols);

        for (int r = 0; r < A.rows; r++) for (int c = 0; c < B.cols; c++) {
            int result = 0;
            for (int j = 0; j < A.cols; j++) {
                result += A.get(r, j) * B.get(j, c);
            }
            resultMat.set(r, c, result);
        }

        return resultMat;
    }
}
