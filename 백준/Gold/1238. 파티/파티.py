# baekjoon settings
from typing import *
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

import math
from queue import PriorityQueue

# init from input
N, M, X = map(int, input().strip().split())
times: Dict[int, Dict[int, int]] = {}
reverseTimes: Dict[int, Dict[int, int]] = {}
for i in range(N):
    times[i] = {}
    reverseTimes[i] = {}
for _ in range(M):
    b, e, t = map(int, input().strip().split())
    times[b-1][e-1] = t
    reverseTimes[e-1][b-1] = t
X -= 1

# dijkstra
dijk = [math.inf for _ in range(N)]
dijk[X] = 0
pq: PriorityQueue[Tuple[int, int]] = PriorityQueue()
pq.put((0, X))
toVisit = {i for i in range(N)}

while toVisit:
    while True:
        _, curr = pq.get()
        if curr in toVisit:
            toVisit.remove(curr)
            break

    for nei, neiT in times[curr].items():
        if dijk[nei] > neiT + dijk[curr]:
            dijk[nei] = neiT + dijk[curr]
            pq.put((dijk[nei], nei))

# reverse dijkstra
reverseDijk = [math.inf for _ in range(N)]
reverseDijk[X] = 0
pq: PriorityQueue[Tuple[int, int]] = PriorityQueue()
pq.put((0, X))
toVisit = {i for i in range(N)}

while toVisit:
    while True:
        _, curr = pq.get()
        if curr in toVisit:
            toVisit.remove(curr)
            break

    for nei, neiT in reverseTimes[curr].items():
        if reverseDijk[nei] > neiT + reverseDijk[curr]:
            reverseDijk[nei] = neiT + reverseDijk[curr]
            pq.put((reverseDijk[nei], nei))

# get max time
print(max(map(lambda i: int(dijk[i] + reverseDijk[i]), range(N))))
