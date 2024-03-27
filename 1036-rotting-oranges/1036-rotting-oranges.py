from typing import *

class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])

        rottenQueue = []
        maxMinute = 0

        # find rottens
        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == 2:
                    rottenQueue.append((i, j))

        # propagate rottens
        while rottenQueue:
            newQueue = []
            for ri, rj in rottenQueue:
                for ni, nj in [(ri+1, rj), (ri, rj+1), (ri-1, rj), (ri, rj-1)]:
                    if (0 <= ni < rows) and (0 <= nj < cols) and (grid[ni][nj] == 1):
                        grid[ni][nj] = 2
                        newQueue.append((ni, nj))

            rottenQueue = newQueue
            if rottenQueue:
                maxMinute += 1

        # recheck for fresh orange
        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == 1:
                    return -1

        return maxMinute

        