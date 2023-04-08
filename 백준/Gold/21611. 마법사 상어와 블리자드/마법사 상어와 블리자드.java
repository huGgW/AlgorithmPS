import java.util.*;

public class Main {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		Grid grid = new Grid(N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid.map[i][j] = sc.nextInt();
			}
		}
		grid.trainBuilder();
		
		int[] exploded = new int[3];
		for (int i = 0; i < M; i++) {
			// Blizzard
			grid.blizzard(sc.nextInt(),  sc.nextInt());
			
			// Bomb
			grid.bomb(exploded);
			
			// Mutant
			grid.mutant();
			
//			System.out.println(grid);
		}
		
		System.out.println(exploded[0] + (exploded[1] * 2) + (exploded[2] * 3));
	}
}

class Coord {
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	public int r;
	public int c;
	
	public Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public Coord next(int dir) {
		switch (dir) {
		case UP:
			return new Coord(r-1, c);
		case DOWN:
			return new Coord(r+1, c);
		case LEFT:
			return new Coord(r, c-1);
		case RIGHT:
			return new Coord(r, c+1);
		}
		return null;
	}
	
	public int valueOn(int[][] grid) {
		return grid[r][c];
	}
}

class Grid {
	public int N;
	public int[][] map;
	public ArrayList<Coord> train;
	public int elemCnt = 0;
	
	public Grid(int N) {
		this.N = N;
		map = new int[N][N];
		train = new ArrayList<>(N*N - 1);
	}
	
	public boolean isIn(Coord c) {
		return 0 <= c.r
				&& c.r < N
				&& 0 <= c.c
				&& c.c < N;
	}
	public int getVal(Coord c) {
		return map[c.r][c.c];
	}
	public void setVal(Coord c, int m) {
		map[c.r][c.c] = m; 
	}
	
	public void trainBuilder() {
		Coord current = new Coord(N / 2, N / 2 - 1);
		int len = 1;
		int dir = Coord.DOWN;
		
		int cnt = 0;
		while (isIn(current)) {
			train.add(current);
			if (getVal(current) != 0) elemCnt++;
			
			current = current.next(dir);
			cnt++;
			
			if (cnt == len) {
				cnt = 0;
				switch (dir) {
				case Coord.DOWN:
					dir = Coord.RIGHT;
					len++;
					break;
				case Coord.RIGHT:
					dir = Coord.UP;
					break;
				case Coord.UP:
					dir = Coord.LEFT;
					len++;
					break;
				case Coord.LEFT:
					dir = Coord.DOWN;
					break;
				}
			}
		}
	}
	
	public void blizzard(int d, int s) {
		// Destroy
		Coord bim = new Coord(N/2, N/2);
		boolean modified = false;
		
		for (int i = 0; i < s; i++) {
			bim = bim.next(d);
			if (getVal(bim) != 0) {
				setVal(bim, 0);
				elemCnt--;
				modified = true;
			}
		}
		
		// Fill
		if (modified) {
			fillMap();
		}
	}
	
	public void bomb(int[] exploded) {
		while (true) {
			// Explode
			int b = 0;
			int e = 0;
			int limit = elemCnt;
			for (int i = 0; i < limit; i++) {
				if (getVal(train.get(b)) == getVal(train.get(i))) {
					e = i;
				} else if (e - b + 1 >= 4) {
					for (int j = b; j <= e; j++) {
						elemCnt--;
						exploded[getVal(train.get(j))-1]++;
						setVal(train.get(j), 0);
					}
					b = i;
					e = i;
				} else {
					b = i;
					e = i;
				}
			}
			
			if (e - b + 1 >= 4) {
				for (int j = b; j <= e; j++) {
					elemCnt--;
					exploded[getVal(train.get(j))-1]++;
					setVal(train.get(j), 0);
				}
			}
			
			// fill
			if (elemCnt < limit) {
				fillMap();
			} else {
				break;
			}
		}
	}
	
	public void fillMap() {
		int valIdx = 0;
		int coordIdx = 0;
		
		while (valIdx < elemCnt) {
			while (getVal(train.get(coordIdx)) == 0) {
				coordIdx++;
			}

			setVal(train.get(valIdx++), getVal(train.get(coordIdx++)));
		}
		
		while (valIdx < coordIdx) {
			setVal(train.get(valIdx++), 0);
		}
	}
	
	public void mutant() {
		Deque<Integer> queue = new LinkedList<>();
		int b = 0;
		int e = 0;
		for (int i = 0; i < elemCnt; i++) {
			if (getVal(train.get(i)) != getVal(train.get(b))) {
				int A = e - b + 1;
				int B = getVal(train.get(b));
				queue.offer(A);
				queue.offer(B);
				b = i;
			}
			e = i;
		}
		if (elemCnt > 0) {
			int A = e - b + 1;
			int B = getVal(train.get(b));
			queue.offer(A);
			queue.offer(B);
		}
		
		int origElemCnt = elemCnt;
		elemCnt = queue.size();
		elemCnt = train.size() < elemCnt ? train.size() : elemCnt;
		for (int i = 0; i < elemCnt; i++) {
			setVal(train.get(i), queue.poll());
		}
		for (int i = elemCnt; i < origElemCnt; i++) {
			setVal(train.get(i), 0);
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int[] arr : map) {
			for (int x : arr) {
				s += String.format("%d ", x);
			}
			s += "\n";
		}
		
		return s;
	}
}