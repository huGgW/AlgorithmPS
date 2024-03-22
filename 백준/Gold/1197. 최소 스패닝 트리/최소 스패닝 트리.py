from typing import *
import sys
import heapq

input = sys.stdin.readline
sys.setrecursionlimit(10**6)

V, E = map(int, input().split())
graph = {
    v: {} for v in range(1, V+1)
}

for _ in range(E):
    f, t, c = map(int, input().split())
    if t in graph[f]:
        graph[f][t] = min(graph[f][t], c)
        graph[t][f] = min(graph[t][f], c)
    else:
        graph[f][t] = c
        graph[t][f] = c

# Prim's algorithm
hp = []
visited = {1}
nextV = 1
weightSum = 0
while len(visited) < V:
    edges = graph[nextV]
    for u, w in edges.items():
        if u not in visited:
            heapq.heappush(hp, (w, u))

    while True:
        ww, uu = heapq.heappop(hp)
        if uu not in visited:
            visited.add(uu)
            nextV = uu
            weightSum += ww
            break

print(weightSum)
