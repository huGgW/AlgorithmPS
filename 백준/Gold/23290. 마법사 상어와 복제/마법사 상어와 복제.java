import java.io.*;
import java.util.*;

public class Main {
	static final int LEFT = 0;
	static final int LEFTUP = 1;
	static final int UP = 2;
	static final int RIGHTUP = 3;
	static final int RIGHT = 4;
	static final int RIGHTDOWN = 5;
	static final int DOWN = 6;
	static final int LEFTDOWN = 7;
	
	static final int SHARKUP = 1;
	static final int SHARKLEFT = 2;
	static final int SHARKDOWN = 3;
	static final int SHARKRIGHT = 4;
	
	public static void main(String[] args) {
		// READ
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int S = sc.nextInt();

		LinkedList<Integer>[][] fish = new LinkedList[4][4];
		Deque<Integer>[][] smell = new Deque[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				fish[i][j] = new LinkedList<>();
				smell[i][j] = new LinkedList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			int d = sc.nextInt() - 1;
			fish[y][x].add(d);
		}
		
		int sharkY = sc.nextInt() - 1;
		int sharkX = sc.nextInt() - 1;
		Coord shark = new Coord(sharkX, sharkY);
		
		sc.close();
		
		for (int pr = 0; pr < S; pr++) {
			// 1. Make moveFish for repliacation
			LinkedList<Integer>[][] moveFish = new LinkedList[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					moveFish[i][j] = new LinkedList<>();
				}
			}
			
			// 2. Move Fish
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					for (int dir: fish[y][x]) {
						int cnt = 0;
						Coord nextFish = null;
						for (; cnt < 8; cnt++) {
							nextFish = nextCoordFish(dir, new Coord(x, y));
							if (nextFish.isIn()
								&& (!nextFish.equals(shark))
								&& smell[nextFish.y][nextFish.x].isEmpty()
								) {
								break;
							}
							dir = (dir + 7) % 8;
						}
						if (cnt < 8) {
							moveFish[nextFish.y][nextFish.x].add(dir); 
						} else if (cnt == 8) {
							moveFish[y][x].add(dir);
						}
					}
				}
			}
			
			// 3. Move Shark
			int maxPath = -1;
			int maxFishCnt = -1;
			// Calculate max path
			for (int f = 1; f <= 4; f++) {
				Coord fShark = nextCoordShark(f, shark);
				if (!fShark.isIn()) { continue; }
				int fCnt = moveFish[fShark.y][fShark.x].size();
				
				for (int s = 1; s <= 4; s++) {
					Coord sShark = nextCoordShark(s, fShark);
					if (!sShark.isIn()) { continue; }
					int sCnt = fCnt + moveFish[sShark.y][sShark.x].size();
					
					for (int t = 1; t <= 4; t++) {
						Coord tShark = nextCoordShark(t, sShark);
						if (!tShark.isIn()) { continue; }
						int tCnt = sCnt;
						if (!tShark.equals(fShark)) {
							tCnt += moveFish[tShark.y][tShark.x].size();
						}
						
						// Change maxPath
						int path = f * 100 + s * 10 + t;
						if (maxFishCnt == -1 || maxFishCnt < tCnt) {
							maxFishCnt = tCnt;
							maxPath = path;
						} else if (maxFishCnt == tCnt && maxPath > path) {
							maxPath = path;
						}
					}
				}
			}
			// Move shark
			for (int i = 0; i < 3; i++) {
				int div = 1;
				for (int d = 0; d < 2 - i; d++) {
					div *= 10;
				}
				int sharkDir = maxPath / div;
				shark = nextCoordShark(sharkDir, shark);
				
				// remove fish, and add smell
				for (int j = 0; j < moveFish[shark.y][shark.x].size(); j++) {
					smell[shark.y][shark.x].offer(pr);
				}
				moveFish[shark.y][shark.x] = new LinkedList<>(); 
				
				maxPath -= sharkDir * div;
			}
			
			// 4. Remove smell older than 2
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					while (!smell[y][x].isEmpty() && smell[y][x].peek() <= pr-2) {
						smell[y][x].poll();
					}
				}
			}
			
			// 5. Merge replica
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					fish[y][x].addAll(moveFish[y][x]);
				}
			}
		}
		
		int fishCnt = 0;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				fishCnt += fish[y][x].size();
			}
		}
		System.out.println(fishCnt);
	}
	
	static Coord nextCoordFish(int dir, Coord c) {
		int x = c.x;
		int y = c.y;
		
		switch(dir) {
		case LEFT:
			x--;
			break;
		case LEFTUP:
			x--;
			y--;
			break;
		case UP:
			y--;
			break;
		case RIGHTUP:
			x++;
			y--;
			break;
		case RIGHT:
			x++;
			break;
		case RIGHTDOWN:
			x++;
			y++;
			break;
		case DOWN:
			y++;
			break;
		case LEFTDOWN:
			x--;
			y++;
			break;
		}
		
		return new Coord(x, y);
	}
	
	static Coord nextCoordShark(int sharkDir, Coord c) {
		int x = c.x;
		int y = c.y;
		
		switch (sharkDir) {
		case SHARKUP:
			y--;
			break;
		case SHARKDOWN:
			y++;
			break;
		case SHARKLEFT:
			x--;
			break;
		case SHARKRIGHT:
			x++;
			break;
		}
		
		return new Coord(x, y);
	}
}


class Coord {
	int x;
	int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isIn() {
		return 0 <= x
			&& x < 4
			&& 0 <= y
			&& y < 4;
	}
	
	@Override
 	public boolean equals(Object other) {
		return (
			this.x == ((Coord)other).x
			&& this.y == ((Coord)other).y
		);
	}
}