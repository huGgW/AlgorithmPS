from typing import *
from pprint import pprint
from collections import deque
import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush


def maxDocs(building: List[List[str]], r: int, c: int, keys: Set[str]) -> int:
    updated = True
    docs = 0

    while updated:
        updated = False
        stack: Deque[Tuple[int, int]] = deque()
        visited: Set[Tuple[int, int]] = set()

        # add all possible edges to stack
        # left, right
        for i in range(r):
            if not isWall(i, 0, building):
                stack.append((i, 0))
            if not isWall(i, c-1, building):
                stack.append((i, c-1))

        # up, down
        for j in range(c):
            if not isWall(0, j, building):
                stack.append((0, j))

            if not isWall(r-1, j, building):
                stack.append((r-1, j))

        # traverse
        while stack:
            i, j = stack.pop()
            visited.add((i, j))

            # door
            if isDoor(i, j, building):
                if isOpenable(i, j, building, keys):
                    updated = True
                    building[i][j] = '.'
                else:
                    continue

            # keys
            if isKey(i, j, building):
                keys.add(building[i][j])
                updated = True
                building[i][j] = '.'

            # docs
            if isDoc(i, j, building):
                docs += 1
                updated = True
                building[i][j] = '.'

            for (ni, nj) in neighbors(i, j):
                if isInRange(ni, nj, r, c) and not isWall(ni, nj, building) and (ni, nj) not in visited:
                    stack.append((ni, nj))

    return docs


def isDoor(i: int, j: int, building: List[List[str]]) -> bool:
    return building[i][j].isalpha() and building[i][j].isupper()

def isKey(i: int, j: int, building: List[List[str]]) -> bool:
    return building[i][j].isalpha() and building[i][j].islower()

def isDoc(i: int, j: int, building: List[List[str]]) -> bool:
    return building[i][j] == '$'

def isOpen(i: int, j: int, building: List[List[str]]) -> bool:
    return building[i][j] == '.'

def isWall(i: int, j: int, building: List[List[str]]) -> bool:
    return building[i][j] == '*'

def isOpenable(i: int, j: int, building: List[List[str]], keys: Set[str]) -> bool:
    return building[i][j].lower() in keys

def isInRange(i: int, j: int, r: int, c: int) -> bool:
    return 0 <= i < r and 0 <= j < c

def neighbors(i: int, j: int) -> List[Tuple[int, int]]:
    return [
        (i-1, j),
        (i, j-1),
        (i+1, j),
        (i, j+1),
    ]


if __name__ == "__main__":
    tc = int(input())

    for _ in range(tc):
        r, c = map(int, input().split())
        building = [
            list(input().strip())
            for _ in range(r)
        ]
        keys = set(input().strip())
        md = maxDocs(building, r, c, keys)
        output(f"{md}\n")

    flush()

