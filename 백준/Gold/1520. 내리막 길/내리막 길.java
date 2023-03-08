import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                System.in
            )
        );

        // Read inputs
        String[] tmp = reader.readLine().trim().split(" ");
        int M = Integer.parseInt(tmp[0]);
        int N = Integer.parseInt(tmp[1]);

        int[][] map = readMap(reader, M, N);
        int[][] paths = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    paths[i][j] = 1;
                } else {
                    paths[i][j] = -1;
                }
            }
        }

        Coord dst = new Coord(M-1, N-1);
        countPaths(map, paths, M, N, dst);
        System.out.println(dst.getCoordVal(paths));
    }

    static int[][] readMap(BufferedReader reader, int M, int N) throws IOException {
        int[][] map = new int[M][N];
        for (int i = 0; i < M; i++) {
            String[] tmp = reader.readLine().trim().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        return map;
    }

    static void countPaths(int[][] map, int[][] paths, int M, int N, Coord current) {
        if (current.getCoordVal(paths) != -1) { return; }

        int cnt = 0;
        for (Coord c : current.getNeighbors(M, N)) {
            if (c.getCoordVal(map) > current.getCoordVal(map)) {
                if (c.getCoordVal(paths) == -1) {
                    countPaths(map, paths, M, N, c);
                }
                cnt += c.getCoordVal(paths);
            }
        }

        current.setCoordVal(paths, cnt);
    }
}

class Coord {
    public int i;
    public int j;

    public Coord(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public ArrayList<Coord> getNeighbors(int M, int N) {
        ArrayList<Coord> list = new ArrayList<>();
        if (0 < i) {
            list.add(new Coord(i-1, j));
        }
        if (0 < j) {
            list.add(new Coord(i, j-1));
        }
        if (i < M-1) {
            list.add(new Coord(i+1, j));
        }
        if (j < N-1) {
            list.add(new Coord(i, j+1));
        }
        return list;
    }

    public int getCoordVal(int[][] table) {
        return table[i][j];
    }

    public void setCoordVal(int[][] table, int val) {
         table[i][j] = val;
    }

    public boolean equals(Coord other) {
        return (this.i == other.i && this.j == other.j);
    }
}