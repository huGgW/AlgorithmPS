from typing import *

inputStr = input()
n, k = map(
        int,
        inputStr.strip().split()
    )

upperBound = 100000
lowerBound = 0

memo: Dict[int, int] = {}

queue: List[Tuple[int, int]] = [(n, 0)]
while queue[0][0] != k:
    top = queue.pop(0)
    # print(top)
    for v in [top[0]*2, top[0]+1, top[0]-1]:
        if lowerBound <= v <= upperBound:
            if v not in memo:
                memo[v] = top[1]+1
                queue.append((v, top[1]+1))

print(queue[0][1])
