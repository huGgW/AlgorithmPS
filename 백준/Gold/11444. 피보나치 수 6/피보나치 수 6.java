/*****************************************************************
 *                       1    1         
 *  F(n-1)   F(n-2)   X              =  F(n-1)+F(n-2)   F(n-1)
 *                       1    0
 ****************************************************************/

import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BigInteger n = new BigInteger(new Scanner(System.in).next());
        final int z = 1000000007;

        Matrix fiboMat = new Matrix(
            new long[][]{
                {1, 0}
        });
        Matrix filter = new Matrix(
            new long[][]{
                {1, 1},
                {1, 0}
            }
        );

        fiboMat = Matrix.mult(fiboMat, Matrix.powerDiv(filter, n, z));
        fiboMat.remainderAll(z);

        System.out.print(fiboMat.get(0, 1));
    }
}

class Matrix {
    int rows;
    int cols;
    long[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows; this.cols = cols;
        data = new long[rows][cols];
    }

    public Matrix(long[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    public long get(int r, int c) { return data[r][c]; }
    public void set(int r, int c, long x) { data[r][c] = x; }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                s += Long.toString(data[i][j]) + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static Matrix identity(int size) {
        Matrix matrix = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            matrix.set(i, i, 1);
        }
        return matrix;
    }

    public void remainderAll(int z) {
        for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++) {
            data[i][j] %= z;
        }
    }

    public static Matrix mult(Matrix A, Matrix B) throws Exception {
        if (A.cols != B.rows) { throw (new Exception()); }

        Matrix multMat = new Matrix(A.rows, B.cols);

        for (int i = 0; i < A.rows; i++) for (int k = 0; k < B.cols; k++) {
            long elem = 0;
            for (int j = 0; j < A.cols; j++) {
                elem += A.get(i, j) * B.get(j, k);
            }
            multMat.set(i, k, elem);
        }

        return multMat;
    }

    public static Matrix powerDiv(Matrix A, BigInteger p, int z) throws Exception {
        if (A.cols != A.rows) { throw new Exception(); }
        Matrix poweredMat = Matrix.identity(A.rows);
        while (p.compareTo(BigInteger.ZERO) > 0) {
            if (p.remainder(BigInteger.TWO).equals(BigInteger.ONE)) {
                poweredMat = Matrix.mult(poweredMat, A);
                poweredMat.remainderAll(z);
            }
            p = p.divide(BigInteger.TWO);
            A = Matrix.mult(A, A);
            A.remainderAll(z);
        }
        return poweredMat;
    }
}