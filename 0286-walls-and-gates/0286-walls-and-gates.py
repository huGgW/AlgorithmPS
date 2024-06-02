from collections import deque

WALL = -1 
GATE = 0

class Solution:
    def wallsAndGates(self, rooms: list[list[int]]) -> None:
        """
        Do not return anything, modify rooms in-place instead.
        """
        n, m = len(rooms), len(rooms[0])

        queue: deque[tuple[int, int]] = deque()
        for i in range(n):
            for j in range(m):
                if rooms[i][j] == GATE:
                    queue.append((i, j))

        while queue:
            i, j = queue.popleft()
            currDist = rooms[i][j]
            for ni, nj in [
                (i-1, j),
                (i, j-1),
                (i+1, j),
                (i, j+1),
            ]:
                if not (0 <= ni < n and 0 <= nj < m):
                    continue
                if rooms[ni][nj] == WALL:
                    continue

                if currDist + 1 < rooms[ni][nj]:
                    rooms[ni][nj] = currDist + 1
                    queue.append((ni, nj))
