from typing import *
from queue import PriorityQueue
import sys
input = sys.stdin.readline

# (Dist, From, To)
edges = PriorityQueue()

N, M = map(int, input().split(" "))
for _ in range(M):
    f, t, d = map(int, input().split(" "))
    edges.put((d, f, t))

groups: List[Set[int]] = []
def findGroupIdx(x: int) -> int:
    for i in range(len(groups)):
        if x in groups[i]:
            return i

    return -1

def mergeGroup(idx1: int, idx2: int):
    global groups
    smallIdx = min(idx1, idx2)
    bigIdx = max(idx1, idx2)
    groups[smallIdx] = groups[smallIdx].union(groups[bigIdx])
    del groups[bigIdx]

dist = 0
while not groups or len(groups[0]) < N:
    d, f, t = edges.get()
    fIdx = findGroupIdx(f)
    tIdx = findGroupIdx(t)

    if fIdx == -1 and tIdx == -1:
        groups.append({f, t})
    elif fIdx == -1 and tIdx != -1:
        groups[tIdx].add(f)
    elif fIdx != -1 and tIdx == -1:
        groups[fIdx].add(t)
    elif fIdx != tIdx:
        mergeGroup(fIdx, tIdx)
    else:
        continue

    dist += d

print(dist)
