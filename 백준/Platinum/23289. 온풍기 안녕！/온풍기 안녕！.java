import java.util.*;

public class Main {
	public static void main(String[] args) {
		// Read
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int column = sc.nextInt();
		int K = sc.nextInt();

		int[][] grid = new int[row][column];
		LinkedList<Warmer> warmers = new LinkedList<>();
		Deque<Coord> checks = new LinkedList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int input = sc.nextInt();
				if (input == 5) {
					checks.offer(new Coord(i, j));
				} else if (input != 0){
					warmers.add(new Warmer(i, j, input));
				}
			}
		}

		int wallCnt = sc.nextInt();
		HashSet<Wall> wallSet = new HashSet<>();
		for (int i = 0; i < wallCnt; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			if (sc.nextInt() == 0) {
				wallSet.add(new Wall(new Coord(r, c), Warmer.UP));
			} else {
				wallSet.add(new Wall(new Coord(r, c), Warmer.RIGHT));
			}
		}
		sc.close();
		
		// Steps
		int choco = 0;
		for (; choco <= 100 && !isOverK(grid, K, checks); choco++) {
			for (Warmer w : warmers) {
				boolean[][] isHeated = w.markHeated(row, column, wallSet);
				w.heatUp(grid, isHeated);
			}
//			System.out.println(String.format("\n%d heat ======================================================", choco+1));
//			printGrid(grid);
//			System.out.println(String.format("===========================================================", choco+1));
			
			controlTemp(grid, wallSet);
//			System.out.println(String.format("\n%d control ======================================================", choco+1));
//			printGrid(grid);
//			System.out.println(String.format("===========================================================", choco+1));
			
			dryEdge(grid);
//			System.out.println(String.format("\n%d edge ======================================================", choco+1));
//			printGrid(grid);
//			System.out.println(String.format("===========================================================", choco+1));

		}

		System.out.println(choco);
	}

//	static void printGrid(int[][] grid) {
//		for (int[] arr : grid) {
//			for (int x : arr) {
//				System.out.print(String.format("%5d ", x));
//			}
//			System.out.println();
//		}
//	}
	
	static void controlTemp(int[][] grid, HashSet<Wall> wallSet) {
		int row = grid.length;
		int column = grid[0].length;
		int[][] transGrid = new int[row][column];
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < column; c++) {
				Coord coord = new Coord(r, c);
				for (int d = 1; d <= 4; d++) {
					Coord next = coord.nextCoord(d);
					if (next.isIn(row, column) && (!wallSet.contains(new Wall(coord, d)))) {
						int diff = Math.abs(grid[coord.r][coord.c] - grid[next.r][next.c]);
						if (grid[coord.r][coord.c] > grid[next.r][next.c] ) {
							transGrid[coord.r][coord.c] -= diff / 4;
						} else {
							transGrid[coord.r][coord.c] += diff / 4;
						}
					}
				}
			}
		}
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < column; c++) {
				grid[r][c] += transGrid[r][c];
			}
		}
	}
	
	static void dryEdge(int[][] grid) {
		int row = grid.length;
		int column = grid[0].length;
		
		for (int r = 0; r < row; r++) {
			if (r == 0 || r == row-1) {
				for (int c = 0; c < column; c++) {
					grid[r][c] = (grid[r][c] != 0) ? grid[r][c] - 1 : grid[r][c];
				}
			} else {
				grid[r][0] = (grid[r][0] != 0) ? grid[r][0] - 1 : grid[r][0];
				grid[r][column-1] = (grid[r][column-1] != 0) ? grid[r][column-1] - 1 : grid[r][column-1];
			}
		}
	}
	
	static boolean isOverK(int[][] grid, int K, Deque<Coord> checks) {
		int n = checks.size();
		for (int i = 0; i < n; i++) {
			Coord c = checks.poll();
			if (grid[c.r][c.c] < K) {
				checks.offer(c);
				return false;
			}
		}

		return checks.isEmpty();
	}
}

class Coord {
	public int r;
	public int c;

	public Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public boolean isIn(int row, int column) {
		return (0 <= r && r < row && 0 <= c && c < column);
	}
	
	public Coord nextCoord(int dir) {
		Coord next = null;
		switch (dir) {
		case Warmer.RIGHT:
			next = new Coord(r, c+1);
			break;
		case Warmer.LEFT:
			next = new Coord(r, c-1);
			break;
		case Warmer.UP:
			next = new Coord(r-1, c);
			break;
		case Warmer.DOWN:
			next = new Coord(r+1, c);
			break;
		}
		
		return next;
	}

	@Override
	public int hashCode() {
		return (Integer.hashCode(r) * 3 + Integer.hashCode(c) * 2) % 8192;
	}

	@Override
	public boolean equals(Object other) {
		return r == ((Coord) other).r && c == ((Coord) other).c;
	}
}

class Warmer extends Coord {
	static final int RIGHT = 1;
	static final int LEFT = 2;
	static final int UP = 3;
	static final int DOWN = 4;

	public int dir;

	public Warmer(int r, int c, int dir) {
		super(r, c);
		this.dir = dir;
	}

	public boolean[][] markHeated(int row, int column, HashSet<Wall> wallSet) {
		boolean[][] isHeated = new boolean[row][column];
		switch (dir) {
		case RIGHT:
			if (c <= column - 2 && !wallSet.contains(new Wall(new Coord(this.r, this.c), Warmer.RIGHT))) {
				isHeated[r][c + 1] = true;
			} else {
				return isHeated;
			}
			for (int c = this.c + 2; c <= Math.min(this.c + 5, column - 1); c++) {
				int add = c - this.c - 1;
				for (int r = Math.max(this.r - add, 0); r <= Math.min(this.r + add, row - 1); r++) {
					Coord leftUp = new Coord(r - 1, c - 1);
					Coord left = new Coord(r, c - 1);
					Coord leftDown = new Coord(r + 1, c - 1);
					if ((leftUp.isIn(row, column) && isHeated[leftUp.r][leftUp.c]
							&& (!wallSet.contains(new Wall(leftUp, Warmer.DOWN)))
							&& (!wallSet.contains(new Wall(left, Warmer.RIGHT)))) // leftup
							|| (left.isIn(row, column) && isHeated[left.r][left.c]
									&& (!wallSet.contains(new Wall(left, Warmer.RIGHT)))) // left
							|| (leftDown.isIn(row, column) && isHeated[leftDown.r][leftDown.c]
									&& (!wallSet.contains(new Wall(leftDown, Warmer.UP)))
									&& (!wallSet.contains(new Wall(left, Warmer.RIGHT)))) // leftdown
					) {
						isHeated[r][c] = true;
					}
				}
			}
			break;
		case LEFT:
			if (c >= 1 && !wallSet.contains(new Wall(new Coord(this.r, this.c), Warmer.LEFT))) {
				isHeated[r][c - 1] = true;
			} else {
				return isHeated;
			}
			for (int c = this.c - 2; c >= Math.max(this.c - 5, 0); c--) {
				int add = this.c - c - 1;
				for (int r = Math.max(this.r - add, 0); r <= Math.min(this.r + add, row - 1); r++) {
					Coord rightUp = new Coord(r - 1, c + 1);
					Coord right = new Coord(r, c + 1);
					Coord rightDown = new Coord(r + 1, c + 1);
					if ((right.isIn(row, column) && isHeated[right.r][right.c]
							&& (!wallSet.contains(new Wall(right, Warmer.LEFT)))
							|| (rightUp.isIn(row, column)) && isHeated[rightUp.r][rightUp.c]
									&& (!wallSet.contains(new Wall(rightUp, Warmer.DOWN)))
									&& (!wallSet.contains(new Wall(right, Warmer.LEFT))))
							|| (rightDown.isIn(row, column)) && isHeated[rightDown.r][rightDown.c]
									&& (!wallSet.contains(new Wall(rightDown, Warmer.UP)))
									&& (!wallSet.contains(new Wall(right, Warmer.LEFT)))) {
						isHeated[r][c] = true;
					}
				}
			}
			break;
		case UP:
			if (r >= 1 && !wallSet.contains(new Wall(new Coord(this.r, this.c), Warmer.UP))) {
				isHeated[r - 1][c] = true;
			} else {
				return isHeated;
			}
			for (int r = this.r - 2; r >= Math.max(this.r - 5, 0); r--) {
				int add = this.r - r - 1;
				for (int c = Math.max(this.c - add, 0); c <= Math.min(this.c + add, column - 1); c++) {
					Coord down = new Coord(r + 1, c);
					Coord downLeft = new Coord(r + 1, c - 1);
					Coord downRight = new Coord(r + 1, c + 1);

					if ((down.isIn(row, column) && isHeated[down.r][down.c]
							&& (!wallSet.contains(new Wall(down, Warmer.UP))))
							|| (downLeft.isIn(row, column) && isHeated[downLeft.r][downLeft.c]
									&& (!wallSet.contains(new Wall(downLeft, Warmer.RIGHT)))
									&& (!wallSet.contains(new Wall(down, Warmer.UP))))
							|| (downRight.isIn(row, column) && isHeated[downRight.r][downRight.c]
									&& (!wallSet.contains(new Wall(downRight, Warmer.LEFT)))
									&& (!wallSet.contains(new Wall(down, Warmer.UP))))) {
						isHeated[r][c] = true;
					}
				}
			}
			break;
		case DOWN:
			if (r <= row - 2 && !wallSet.contains(new Wall(new Coord(this.r, this.c), Warmer.DOWN))) {
				isHeated[r + 1][c] = true;
			} else {
				return isHeated;
			}
			for (int r = this.r + 2; r <= Math.min(this.r + 5, row - 1); r++) {
				int add = r - this.r - 1;
				for (int c = Math.max(this.c - add, 0); c <= Math.min(this.c + add, column - 1); c++) {
					Coord up = new Coord(r - 1, c);
					Coord upLeft = new Coord(r - 1, c - 1);
					Coord upRight = new Coord(r - 1, c + 1);

					if ((up.isIn(row, column) && isHeated[up.r][up.c] && (!wallSet.contains(new Wall(up, Warmer.DOWN))))
							|| (upLeft.isIn(row, column) && isHeated[upLeft.r][upLeft.c])
									&& (!wallSet.contains(new Wall(upLeft, Warmer.RIGHT)))
									&& (!wallSet.contains(new Wall(up, Warmer.DOWN)))
							|| (upRight.isIn(row, column) && isHeated[upRight.r][upRight.c])
									&& (!wallSet.contains(new Wall(upRight, Warmer.LEFT)))
									&& (!wallSet.contains(new Wall(up, Warmer.DOWN)))) {
						isHeated[r][c] = true;
					}
				}
			}
			break;
		}

		return isHeated;
	}

	public void heatUp(int[][] grid, boolean[][] isHeated) {
		switch (dir) {
		case RIGHT:
			for (int c = this.c + 1; c <= Math.min(this.c + 5, grid[0].length - 1); c++) {
				int addT = 6 - (c - this.c);
				for (int r = Math.max(this.r - (5 - addT), 0); r <= Math.min(this.r + (5 - addT),
						grid.length - 1); r++) {
					if (isHeated[r][c]) {
						grid[r][c] += addT;
					}
				}
			}
			break;
		case LEFT:
			for (int c = this.c - 1; c >= Math.max(this.c - 5, 0); c--) {
				int addT = 6 - (this.c - c);
				for (int r = Math.max(this.r - (5 - addT), 0); r <= Math.min(this.r + (5 - addT),
						grid.length - 1); r++) {
					if (isHeated[r][c]) {
						grid[r][c] += addT;
					}
				}
			}
			break;
		case UP:
			for (int r = this.r - 1; r >= Math.max(this.r - 5, 0); r--) {
				int addT = 6 - (this.r - r);
				for (int c = Math.max(this.c - (5 - addT), 0); c <= Math.min(this.c + (5 - addT),
						grid[0].length - 1); c++) {
					if (isHeated[r][c]) {
						grid[r][c] += addT;
					}
				}
			}
			break;
		case DOWN:
			for (int r = this.r + 1; r <= Math.min(this.r + 5, grid.length - 1); r++) {
				int addT = 6 - (r - this.r);
				for (int c = Math.max(this.c - (5 - addT), 0); c <= Math.min(this.c + (5 - addT),
						grid[0].length - 1); c++) {
					if (isHeated[r][c]) {
						grid[r][c] += addT;
					}
				}
			}
			break;
		}
	}
}

class Wall {
	Coord c1;
	Coord c2;

	public Wall(Coord c1, Coord c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public Wall(Coord c, int dir) {

		switch (dir) {
		case Warmer.RIGHT:
			this.c1 = c;
			this.c2 = new Coord(c.r, c.c + 1);
			break;
		case Warmer.LEFT:
			this.c1 = new Coord(c.r, c.c - 1);
			this.c2 = c;
			break;
		case Warmer.UP:
			this.c2 = c;
			this.c1 = new Coord(c.r - 1, c.c);
			break;
		case Warmer.DOWN:
			this.c1 = c;
			this.c2 = new Coord(c.r + 1, c.c);
			break;
		}
	}

	@Override
	public int hashCode() {
		return (c1.hashCode() * 5 + c2.hashCode() * 7) % 8192;
	}

	@Override
	public boolean equals(Object other) {
		return c1.equals(((Wall) other).c1) && c2.equals(((Wall) other).c2);
	}
}