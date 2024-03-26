from typing import *
from collections import deque
from pprint import pprint

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        waterq = deque()
        landq = deque()
        islandCnt = 0
        state: str

        if grid[0][0] == "0":
            state = "water"
            waterq.append((0, 0))
        elif grid[0][0] == "1":
            state = "land"
            landq.append((0, 0))
            islandCnt += 1
        grid[0][0] = "-1"

        while waterq or landq:
            if state == "water":
                if landq:
                    state = "land"
                    islandCnt += 1
                    continue
                
                i, j = waterq.popleft()
                for ni, nj in [
                    (i-1, j),
                    (i, j-1),
                    (i+1, j),
                    (i, j+1),
                ]:
                    if not (
                        0 <= ni < len(grid) \
                        and 0 <= nj < len(grid[0])
                    ):
                        continue

                    if grid[ni][nj] == "0":
                        waterq.append((ni, nj))
                        grid[ni][nj] = "-1"
                    elif grid[ni][nj] == "1" and len(landq) == 0:
                        landq.append((ni, nj))
                        grid[ni][nj] = "-1"

            elif state == "land":
                if not landq:
                    state = "water"
                    continue
                
                i, j = landq.popleft()
                for ni, nj in [
                    (i-1, j),
                    (i, j-1),
                    (i+1, j),
                    (i, j+1),
                ]:
                    if not (
                        0 <= ni < len(grid) \
                        and 0 <= nj < len(grid[0])
                    ):
                        continue

                    if grid[ni][nj] == "0" and len(waterq) == 0:
                        waterq.append((ni, nj))
                        grid[ni][nj] = "-1"
                    elif grid[ni][nj] == "1":
                        landq.append((ni, nj))
                        grid[ni][nj] = "-1"

        return islandCnt
        
