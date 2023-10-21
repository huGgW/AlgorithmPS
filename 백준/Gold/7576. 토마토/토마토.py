from typing import *
import sys

n, m = map(
    int,
    sys.stdin.readline().strip().split(" ")
)

# Read grid (with wrap, init eik)
grid: List[List[int]] = [[-1 for _ in range(n+2)]]
lastEiks: Set[Tuple[int, int]] = set()
remainCnt = 0

for i in range(1, m+1):
    grid_row = [-1]
    str_row = sys.stdin.readline().strip().split(" ")
    for j, s in enumerate(str_row):
        if s == "1":
            lastEiks.add((i, j+1))
        elif s == "0":
            remainCnt += 1
        grid_row.append(int(s))
    grid_row.append(-1)
    grid.append(grid_row)

grid.append([-1 for _ in range(n+2)])

# Check Most Far Distance
cnt = 0
while remainCnt > 0 and lastEiks:
    newEiks: Set[Tuple[int, int]] = set()
    for e in lastEiks:
        for t in [
            (e[0]-1, e[1]),
            (e[0]+1, e[1]),
            (e[0], e[1]-1),
            (e[0], e[1]+1),
        ]:
            if grid[t[0]][t[1]] == 0:
                grid[t[0]][t[1]] = 1
                newEiks.add(t)
    remainCnt -= len(newEiks)
    lastEiks = newEiks
    cnt += 1

if len(lastEiks) == 0 and remainCnt > 0:
    sys.stdout.write("-1")
else:
    sys.stdout.write(str(cnt))

sys.stdout.flush()
