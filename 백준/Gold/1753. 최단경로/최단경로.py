from typing import *
import sys
import math as m
import heapq as hq

input = sys.stdin.readline
output = sys.stdout.write

V, E = map(int, input().split(" "))
startV = int(input()) 

# make graph

graph: Dict[int, Dict[int, int]] = {}
for i in range(1, V+1):
    graph[i] = {}

for _ in range(E):
    f, t, w = map(int, input().split(" "))
    existE = graph[f].get(t)
    if existE == None or existE > w:
        graph[f][t] = w

# Djkstra
minDist = {startV: 0}
queue = []
hq.heappush(queue, (0, startV))
toVisit = set([i for i in range(1, V+1)])

while toVisit and queue:
    dist, targetV = hq.heappop(queue)
    if not targetV in toVisit:
        continue

    toVisit.remove(targetV)

    for neighborV in graph[targetV].keys():
        if not neighborV in toVisit:
            continue
        distAfterTarget = dist + graph[targetV][neighborV]
        if distAfterTarget < minDist.get(neighborV, m.inf):
            hq.heappush(queue, (distAfterTarget, neighborV))
            minDist[neighborV] = distAfterTarget

# print output
for v in range(1, V+1):
    output(f"{minDist.get(v, 'INF')}\n")
