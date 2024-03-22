from typing import *
import math
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush
sys.setrecursionlimit(10**6)

N, M = map(int, input().split())
graph = {
    v: {} for v in range(1, N+1)
}

for _ in range(M):
    f, t, c = map(int, input().split())
    if t in graph[f]:
        graph[f][t] = min(graph[f][t], c)
    else:
        graph[f][t] = c

# bellman-ford
dists = [0 if i == 1 else math.inf for i in range(N+1)]
for _ in range(N-1):
    for v in range(1, N+1):
        edges = graph[v]
        for u, w in edges.items():
            dists[u] = min(dists[u], dists[v] + w)

# once more for detect negative cycle 
isNeg = False
for v in range(1, N+1):
    edges = graph[v]
    for u, w in edges.items():
        if dists[v] + w < dists[u]:
            isNeg = True
            break

    if isNeg:
        break

if isNeg:
    output("-1\n")
else:
    for v in range(2, N+1):
        if dists[v] == math.inf:
            output("-1\n")
        else:
            output(f"{dists[v]}\n")
flush()
