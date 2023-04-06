import java.io.*;
import java.util.*;

public class Main {
	
	static final int RIGHT = 0;
	static final int UP = 1;
	static final int LEFT = 2;
	static final int DOWN = 3;
	
	public static void main(String[] args) {
		// Scan
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		// Max, Min fish
		int maxFish = -1;
		int minFish = -1;
		
		ArrayList<Integer> fishes = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			int scanFish = sc.nextInt();
			fishes.add(scanFish);
			// check max, min fish
			maxFish = (maxFish == -1 || maxFish < scanFish) ? scanFish : maxFish;
			minFish = (minFish == -1 || minFish > scanFish) ? scanFish : minFish;
		}
		sc.close();
		
		
		// Find the maximum rolling number
		int width = 1;
		int height = 2;
		while (true) {
			if (width * height > n) {
				height--;
				break;
			}
			if (++width * height > n) {
				width--;
				break;
			}
			height++;
		}
		
		int fixingFishCnt;
		for (fixingFishCnt = 0; maxFish - minFish > k; fixingFishCnt++) {
			/**
			 *  Add fish to minimum tanks
			 */
			for (int i = 0; i < n; i++) {
				if (fishes.get(i) == minFish) {
					fishes.set(i,  fishes.get(i)+1);
				}
			}
//			System.out.print("add fish to min tank : ");
//			System.out.println(fishes);
			
			/**
			 * Rolling fishtank
			 */
			
			// Initialize structured fishtank
			ArrayList<ArrayList<Integer>> fishtanks = new ArrayList<>();
			for (int i = 0; i < height; i++) {
				fishtanks.add(new ArrayList<>());
				if (i == 0) {
					for (int j = 0; j < n - (width * height) + width; j++) {
						fishtanks.get(0).add(0);
					}
				} else {
					for (int j = 0; j < width; j++) {
						fishtanks.get(i).add(0);
					}
				}
			}
			
			// Fill fishes in order
			int w = fishtanks.get(0).size() - 1;
			int h = 0;
			int dir = LEFT;
			int roundCnt = 0;
			for (int c = n-1; c >= 0; c--) {
				fishtanks.get(h).set(w, fishes.get(c));
				switch (dir) {
				case LEFT: {
					if (w == 0 + roundCnt) {
						dir = UP;
						h++;
					} else {
						w--;
					}
					break;
				}
				case UP: {
					if (h == height-1 - roundCnt) {
						dir = RIGHT;
						w++;
					} else {
						h++;
					}
					break;
				}
				case RIGHT: {
					if (w == width-1 - roundCnt) {
						dir = DOWN;
						roundCnt++;
						h--;
					} else {
						w++;
					}
					break;
				}
				case DOWN: {
					if (h == 0 + roundCnt) {
						dir = LEFT;
						w--;
					} else {
						h--;
					}
					break;
				}
				}
			}
			
			// Initialize arraylist moveFish
			ArrayList<ArrayList<Integer>> moveFish = new ArrayList<>(fishtanks.size());
			for (int i = 0; i < fishtanks.size(); i++) {
				moveFish.add(new ArrayList<Integer>(fishtanks.get(i).size()));
				for (int j = 0; j < fishtanks.get(i).size(); j++) {
					moveFish.get(i).add(0);
				}
			}
			// Calculate move fishes
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < fishtanks.get(y).size(); x++) {
					// left
					if (x > 0) {
						moveFish.get(y).set(x,  
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x), 
										fishtanks.get(y).get(x-1)
										)
								);
					}
					// right
					if (x < fishtanks.get(y).size()-1) {
						moveFish.get(y).set(x,  
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x), 
										fishtanks.get(y).get(x+1)
										)
								);
					}
					// up
					if (y < fishtanks.size()-1 && fishtanks.get(y+1).size() > x) {
						moveFish.get(y).set(x,  
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x), 
										fishtanks.get(y+1).get(x)
										)
								);
					}
					// down
					if (y > 0) {
						moveFish.get(y).set(x,  
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x), 
										fishtanks.get(y-1).get(x)
										)
								);
					}
				}
			}
			// Move fish && move fishtank to floor
			int cnt = 0;
			for (int x = 0; x < n - (width * height) + width; x++) {
				for (int y = 0; y < height && fishtanks.get(y).size() > x; y++) {
					int newFish = fishtanks.get(y).get(x) + moveFish.get(y).get(x);
					fishes.set(cnt++, newFish);
				}
			}
//			System.out.print("rolling fish : ");
//			System.out.println(fishes);
			
			/**
			 * Folding fishtank
			 */
			// fishtanks for folding
			fishtanks = new ArrayList<>(4);
			for (int i = 0; i < 4; i++) {
				fishtanks.add(new ArrayList<>());
				for (int j = 0; j < n / 4; j++) {
					int offset = ((i + 3) % 4) * (n / 4);
					int idx = (i % 2 == 0) ? offset + j : (offset + (n / 4)) - j - 1;
					fishtanks.get(i).add(fishes.get(idx));
				}
			}
			// Initialize moveFish
			moveFish = new ArrayList<>(4);
			for (int i = 0; i < 4; i++) {
				moveFish.add(new ArrayList<>(n/4));
				for (int j = 0; j < n/4; j++) {
					moveFish.get(i).add(0);
				}
			}
			// Calculate Move Fish
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < n / 4; x++) {
					// left
					if (x > 0) {
						moveFish.get(y).set(x,
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x),
										fishtanks.get(y).get(x-1)
									)
								);
					}
					// right
					if (x < n/4 - 1) {
						moveFish.get(y).set(x, 
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x),
										fishtanks.get(y).get(x+1)
									)
								);
					}
					// up
					if (y < 3) {
						moveFish.get(y).set(x, 
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x),
										fishtanks.get(y+1).get(x)
									)
								);
					}
					// down
					if (y > 0) {
						moveFish.get(y).set(x, 
								moveFish.get(y).get(x)
								+ getAdjustFish(
										fishtanks.get(y).get(x),
										fishtanks.get(y-1).get(x)
									)
								);
					}
				}
			}
			
			// Move fish && move fishtank to floor && find max, min fish
			cnt = 0;
			maxFish = -1;
			minFish = -1;
			for (int x = 0; x < n/4; x++) {
				for (int y = 0; y < 4; y++) {
					int newFish = fishtanks.get(y).get(x) + moveFish.get(y).get(x);
					fishes.set(cnt++, newFish);
					// check max, min fish
					maxFish = (maxFish == -1 || maxFish < newFish) ? newFish : maxFish;
					minFish = (minFish == -1 || minFish > newFish) ? newFish : minFish;
				}
			}
//			System.out.print("folding fish : ");
//			System.out.println(fishes);
		}
		
		System.out.println(fixingFishCnt);
	}
	
	static int getAdjustFish(int fish, int neighbor) {
		int diff = Math.abs(fish - neighbor);
		int d = diff / 5;
		return (fish > neighbor) ? -d : d;
	}
}