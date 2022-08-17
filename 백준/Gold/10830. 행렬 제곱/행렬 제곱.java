import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = in.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        Matrix A = new Matrix(N, N);
        long B = Long.parseLong(tmp[1]);
        final int z = 1000;

        for (int i = 0; i < N; i++) {
            A.fillRow(i, toIntArr(in.readLine().split(" ")));
        }

        Matrix powered = Matrix.powerDiv(A, B, z);
        System.out.print(powered);
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

    public static Matrix makeIdentity(int size) {
        Matrix ideal = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            ideal.set(i, i, 1);
        }
        return ideal;
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

    public static Matrix powerDiv(Matrix A, long B, int z) throws Exception {
        if (A.rows != A.cols) { throw (new Exception()); }
        Matrix resultMat = Matrix.makeIdentity(A.rows);
        while (B > 0) {
            if (B % 2 == 1) {
                resultMat = Matrix.mult(resultMat, A);
            }
            resultMat.remainderAll(z);
            A = Matrix.mult(A, A);
            A.remainderAll(z);
            B /= 2;
        }
        return resultMat;
    }

    private void remainderAll(int z) {
        for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++) {
            data[i][j] %= z;
        }
    }
}

class ArithDiv {
    public static int sumDiv(int x, int y, int z) {
        return ((x%z) + (y%z)) % z;
    }
    
    public static int multDiv(int x, int y, int z) {
        x %= z;
        int result = 0;
        while (y > 0) {
            if (y % 2 == 1) {
                result = sumDiv(result, x, z);
                x = (x << 1) % 2;
                y /= 2;
            }
        }
        return result;
    }

    public static int powerDiv(int x, int p, int z) {
        x %= z;
        int result = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                result = multDiv(result, x, z);
            }
            x = multDiv(x, x, z);
            p /= 2;
        }
        return result;
    }
}