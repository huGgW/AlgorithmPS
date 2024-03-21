from typing import *
import sys
from collections import deque
from enum import Enum
# from pprint import pprint

sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

class Dir(Enum):
    CENTER = 0
    UP = 1
    LEFT = 2
    DOWN = 3
    RIGHT = 4

    @staticmethod
    def movePoint(orig: 'Dir', dest: 'Dir') -> int:
        if orig.value == dest.value:
            return 1
        elif orig.value == 0:
            return 2
        elif abs(orig.value - dest.value) == 2:
            return 4
        else:
            return 3


def main(commands: List[int]) -> int:
    MAXPOINT: Final = len(commands) * 4
    currentTrack: Dict[Tuple[Dir, Dir], int] = {(Dir.CENTER, Dir.CENTER): 0}
    nextTrack: Dict[Tuple[Dir, Dir], int] = {}

    for nextIdx in range(len(commands)):
        nextDir = Dir(commands[nextIdx])
        for (pos, force) in currentTrack.items():
            # left
            if nextDir != pos[1]:
                leftForce = force + Dir.movePoint(pos[0], nextDir)
                leftPos = (nextDir, pos[1]) if nextDir.value < pos[1].value else (pos[1], nextDir)
                nextTrack[leftPos] = min(nextTrack.get(leftPos, MAXPOINT), leftForce)

            # right
            if nextDir != pos[0]:
                rightForce = force + Dir.movePoint(pos[1], nextDir)
                rightPos = (nextDir, pos[0]) if nextDir.value < pos[1].value else (pos[0], nextDir)
                nextTrack[rightPos] = min(nextTrack.get(rightPos, MAXPOINT), rightForce)

        currentTrack = nextTrack
        nextTrack = {}

    # get the minimum point
    return min(currentTrack.values())


if __name__ == "__main__":
    commands = [int(s) for s in input().split()]
    result = main(commands[:-1])
    output(f"{result}\n")
    flush()
