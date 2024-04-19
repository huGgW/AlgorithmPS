from typing import *
import sys
input = sys.stdin.readline

from enum import Enum, auto
from copy import deepcopy

class Dir(Enum):
    UP = auto()
    LEFT = auto()
    DOWN = auto()
    RIGHT = auto()

def maxBlock2048(N: int, board: List[List[int]], leftShift: int = 5) -> int:
    if leftShift == 0:
        return 0

    maxVal = 0
    for dir in Dir:
        moved, tmpMax, newBoard = shiftTo(dir, board, N)
        if not moved:
            maxVal = max(maxVal, tmpMax)
        else:
            maxVal = max(maxVal, tmpMax, maxBlock2048(N, newBoard, leftShift-1))
    
    return maxVal

def shiftTo(
    dir: Dir, board: List[List[int]], N: int
) -> Tuple[bool, int, Optional[List[List[int]]]]:
    maxVal = 0
    newBoard = deepcopy(board)
    moved = False

    if dir == Dir.UP:
        for j in range(N):
            lastStacked = -1
            mergable = True
            for i in range(N):
                if newBoard[i][j] == 0:
                    continue

                if lastStacked != -1 \
                and newBoard[lastStacked][j] == newBoard[i][j] \
                and mergable:
                    newBoard[lastStacked][j] += newBoard[i][j]
                    newBoard[i][j] = 0
                    mergable = False
                    moved = True
                    maxVal = max(maxVal, newBoard[lastStacked][j])
                else:
                    lastStacked += 1
                    if lastStacked != i:
                        newBoard[lastStacked][j] = newBoard[i][j]
                        newBoard[i][j] = 0
                        moved = True
                    mergable = True
                    maxVal = max(maxVal, newBoard[lastStacked][j])
    elif dir == Dir.DOWN:
        for j in range(N):
            lastStacked = N
            mergable = True
            for i in range(N-1, -1, -1):
                if newBoard[i][j] == 0:
                    continue

                if lastStacked != N \
                and newBoard[lastStacked][j] == newBoard[i][j] \
                and mergable:
                    newBoard[lastStacked][j] += newBoard[i][j]
                    newBoard[i][j] = 0
                    mergable = False
                    moved = True
                    maxVal = max(maxVal, newBoard[lastStacked][j])
                else:
                    lastStacked -= 1
                    if lastStacked != i:
                        newBoard[lastStacked][j] = newBoard[i][j]
                        newBoard[i][j] = 0
                        moved = True
                    mergable = True
                    maxVal = max(maxVal, newBoard[lastStacked][j])
    elif dir == Dir.LEFT:
        for i in range(N):
            ls = newBoard[i]
            lastStacked = -1
            mergable = True
            for j in range(N):
                if ls[j] == 0:
                    continue

                if lastStacked != -1 \
                and ls[lastStacked] == ls[j] \
                and mergable:
                    ls[lastStacked] += ls[j]
                    ls[j] = 0
                    mergable = False
                    moved = True
                    maxVal = max(maxVal, ls[lastStacked])
                else:
                    lastStacked += 1
                    if lastStacked != j:
                        ls[lastStacked] = ls[j]
                        ls[j] = 0
                        moved = True
                    mergable = True
                    maxVal = max(maxVal, ls[lastStacked])
    else: # RIGHT:
        for i in range(N):
            ls = newBoard[i]
            lastStacked = N
            mergable = True
            for j in range(N-1, -1, -1):
                if ls[j] == 0:
                    continue

                if lastStacked != N \
                and ls[lastStacked] == ls[j] \
                and mergable:
                    ls[lastStacked] += ls[j]
                    ls[j] = 0
                    mergable = False
                    moved = True
                    maxVal = max(maxVal, ls[lastStacked])
                else:
                    lastStacked -= 1
                    if lastStacked != j:
                        ls[lastStacked] = ls[j]
                        ls[j] = 0
                        moved = True
                    mergable = True
                    maxVal = max(maxVal, ls[lastStacked])

    if moved:
        return (moved, maxVal, newBoard)
    else:
        return (moved, maxVal, None)

if __name__ == "__main__":
    N = int(input())
    board = [
        [int(s) for s in input().split()]
        for _ in range(N)
    ]

    result = maxBlock2048(N, board)
    print(result)
    
