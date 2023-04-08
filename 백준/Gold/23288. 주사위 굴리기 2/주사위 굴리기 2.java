import java.util.*;

public class Main {
	public static void main(String[] args) {
		// Read & Initailize
		Scanner sc = new Scanner(System.in);
		
		int row = sc.nextInt();
		int column = sc.nextInt();
		int moveNum = sc.nextInt();
		
		Map map = new Map(row, column);
		for (int r = 0; r < map.row; r++) {
			for (int c = 0; c < map.column; c++) {
				map.grid[r][c] = sc.nextInt();
			}
		}
		
		Dice dice = new Dice(6, 5, 3, new Coord(0, 0), Coord.RIGHT);
		
		sc.close();
		
		// Move dice
		int totalScore = 0;
		for (int roll = 0; roll < moveNum; roll++) {
			if (!map.isIn(dice.pos.nextCoord(dice.dir))) {
				dice.flip();
			}
			
			dice.move();
			
			totalScore += map.scoreOf(dice.pos);
			
			int currentMapVal = map.grid[dice.pos.r][dice.pos.c];
			if (dice.current > currentMapVal) {
				dice.rotateClockwise();
			} else if (dice.current < currentMapVal) {
				dice.rotateAntiClockwise();
			}
			
//			System.out.println(String.format("%d %d\n%d\n", dice.current, dice.right, dice.down));
		}

		System.out.println(totalScore);
	}
}

class Coord {
	static final int LEFT = 0;
	static final int DOWN = 1;
	static final int RIGHT = 2;
	static final int UP = 3;
	
	int r;
	int c;
	
	public Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public Coord nextCoord(int dir) {
		switch (dir) {
		case LEFT:
			return new Coord(r, c-1);
		case RIGHT:
			return new Coord(r, c+1);
		case UP:
			return new Coord(r-1, c);
		case DOWN:
			return new Coord(r+1, c);
		}
		return null;
	}
}

class Dice {
	int current;
	int down;
	int right;
	int dir;
	Coord pos;
	
	public Dice(int c, int d, int r, Coord pos, int dir) {
		current = c;
		down = d;
		right = r;
		this.pos = pos;
		this.dir = dir;
	}
	
	public void rotateClockwise() {
		dir = (dir+3) % 4;
	}
	
	public void rotateAntiClockwise() {
		dir = (dir+1) % 4;
	}
	
	public void flip() {
		rotateClockwise();
		rotateClockwise();
	}
	
	public void move() {
		pos = pos.nextCoord(dir);
		
		int newDown = down;
		int newRight = right;
		int newCurrent = current;
		
		switch (dir) {
		case Coord.DOWN:
			newCurrent = down;
			newDown = (7 - current);
			break;
		case Coord.UP:
			newCurrent = (7 - down);
			newDown = current;
			break;
		case Coord.RIGHT:
			newCurrent = right;
			newRight = (7 - current);
			break;
		case Coord.LEFT:
			newCurrent = (7 - right);
			newRight = current;
			break;
		}
		
		down = newDown;
		right = newRight;
		current = newCurrent;
	}
}

class Map {
	int row;
	int column;
	int[][] grid;
	
	public Map(int r, int c) {
		row = r;
		column = c;
		grid = new int[row][column];
	}
	
	public boolean isIn(Coord pos) {
		return 0 <= pos.r
				&& pos.r < row
				&& 0 <= pos.c
				&& pos.c < column;
	}
	
	public int scoreOf(Coord pos) {
		Deque<Coord> queue = new LinkedList<>();
		boolean[][] visited = new boolean[row][column];
		int multiplier = grid[pos.r][pos.c];
		
		int cnt = 0;
		queue.offer(pos);
		while (!queue.isEmpty()) {
			Coord curPos = queue.poll();
			if (visited[curPos.r][curPos.c]) { continue; } 
			visited[curPos.r][curPos.c] = true;
			cnt++;
			
			for (int dir = 0; dir < 4; dir++) {
				Coord nextPos = curPos.nextCoord(dir);
				if (isIn(nextPos) && !visited[nextPos.r][nextPos.c] && grid[nextPos.r][nextPos.c] == multiplier) {
					queue.offer(nextPos);
				}
			}
		}
		
		return cnt * multiplier;
	}
}