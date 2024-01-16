# baekjoon setting
from typing import *
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

from dataclasses import dataclass

@dataclass
class Node:
    parent: Optional[int]
    children: Dict[int, int]

# init tree from input
n = int(input())
nodes: List[Node] = [Node(None, {}) for i in range(n)]
nodes.insert(0, None)

for _ in range(n):
    nums = list(map(int, input().strip().split()))
    node = nodes[nums[0]]
    for i in range(2, len(nums), 2):
        num, dist = nums[i-1], nums[i]
        node.children[num] = dist

# set root as 1 and configure parent
queue = [1]
while queue:
    parent = queue.pop(0)
    node = nodes[parent]

    for child in node.children.keys():
        nodes[child].parent = parent
        del nodes[child].children[parent]
        queue.append(child)

# analyze depth
@dataclass
class PathInfo:
    max: int
    secondMax: int
paths: List[Optional[PathInfo]] = [None for _ in range(n+1)]

def analyzePath(key: int):
    if paths[key] != None:
        return

    node = nodes[key]
    for childKey in node.children.keys():
        analyzePath(childKey)

    childPaths = []
    for childKey, dist in node.children.items():
        childPath = dist + paths[childKey].max
        childPaths.append(childPath)

    if len(childPaths) == 0:
        paths[key] = PathInfo(0, 0)
    elif len(childPaths) == 1:
        paths[key] = PathInfo(childPaths[0], 0)
    else:
        maxIdx = -1
        secondMaxIdx = -1
        for i in range(len(childPaths)):
            if maxIdx == -1 or childPaths[maxIdx] < childPaths[i]:
                maxIdx = i
        for i in range(len(childPaths)):
            if i != maxIdx and \
            (secondMaxIdx == -1 or childPaths[secondMaxIdx] < childPaths[i]):
                secondMaxIdx = i
        paths[key] = PathInfo(childPaths[maxIdx], childPaths[secondMaxIdx])

analyzePath(1)

# get radius of tree
print(max(map(lambda p: p.max + p.secondMax, paths[1:])))
