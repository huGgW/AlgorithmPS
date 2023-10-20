import sys
from typing import Set, List

# 1st line
n, m = map(
        lambda s: int(s),
        sys.stdin.readline().split()
    )

# cases
graph: List[Set[int]] = [None]
notVisited: Set[int] = set()
cnt = 0

for i in range(1, n+1):
    graph.append(set())
    notVisited.add(i)

for _ in range(m):
    x, y = map(
            lambda s: int(s),
            sys.stdin.readline().split()
        )
    graph[x].add(y)
    graph[y].add(x)

def moveThrough(i: int):
    next = graph[i]
    toVisit = next.intersection(notVisited)
    notVisited.difference_update(toVisit)
    for v in toVisit:
        moveThrough(v)


for i in range(1, n+1):
    if i not in notVisited:
        continue

    moveThrough(i)

    cnt += 1

sys.stdout.write(f"{cnt}")
