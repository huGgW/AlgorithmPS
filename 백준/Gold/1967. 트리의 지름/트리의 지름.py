from typing import *
from dataclasses import dataclass
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

@dataclass
class Node:
    key: int
    children: List[Tuple['Node', int]]

@dataclass
class PathAnalysis:
    key: int
    max: int
    secondMax: Optional[int]

n = int(input())
nodes  = {1: Node(1, [])}

# init tree
for _ in range(n-1):
    k, c, d = map(int, input().strip().split())

    if k not in nodes:
        nodes[k] = Node(k, [])

    if c not in nodes:
        nodes[c] = Node(c, [])

    nodes[k].children.append((nodes[c], d))

# path analysis
paths: Dict[int, PathAnalysis] = {}
def pathAnalysis(x: int):
    dists = []

    for childNode, dist in nodes[x].children:
        if childNode.key not in paths:
            pathAnalysis(childNode.key)
        childPath = paths[childNode.key]
        dists.append(childPath.max + dist)

    if len(dists) == 0:
        paths[x] = PathAnalysis(x, 0, None)
    elif len(dists) == 1:
        paths[x] = PathAnalysis(x, dists[0], None)
    else:
        # max idx
        maxIdx = 0
        for i in range(1, len(dists)):
            if dists[i] > dists[maxIdx]:
                maxIdx = i
        maxDist = dists.pop(maxIdx)
        # second max idx
        secondMaxIdx = 0
        for i in range(1, len(dists)):
            if dists[i] > dists[secondMaxIdx]:
                secondMaxIdx = i
        secondMaxDist = dists.pop(secondMaxIdx)

        paths[x] = PathAnalysis(x, maxDist, secondMaxDist)

    # print(f'pathAnalysis({x}) = {paths[x]}')

pathAnalysis(1)

# find max dist
maxDist = 0
for path in paths.values():
    tmpDist = path.max
    if path.secondMax:
        tmpDist += path.secondMax

    if tmpDist > maxDist:
        maxDist = tmpDist

print(maxDist)
