from typing import *
import heapq
import math
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush


V, E = map(int, input().split())
startV = int(input())

# init graph
graph: Dict[int, Dict[int, int]] = {
}

for _ in range(E):
    u, v, w = map(int, input().split())
    if u not in graph:
        graph[u] = dict()

    if v not in graph[u]:
        graph[u][v] = w
    else:
        graph[u][v] = min(w, graph[u][v])

# dijkstra
dists = [0 if v == startV else math.inf for v in range(V+1)]
hp = [(0, startV)]
visited = set()

while len(visited) < V and hp:
    d, v = heapq.heappop(hp)
    if d > dists[v]: # old one
        continue

    visited.add(v)
    edges = graph.get(v, {})
    for (u, w) in edges.items():
        if u in visited:
            continue
        updateDist = d + w
        if updateDist < dists[u]:
            heapq.heappush(hp, (updateDist, u))
            dists[u] = updateDist

# print dists
for i in range(1, V+1):
    output(f"{'INF' if dists[i] == math.inf else dists[i]}\n")
flush()