from typing import *
from dataclasses import dataclass
import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

@dataclass(frozen=True)
class StairState:
    last: int
    exists: tuple[bool, ...]

    def nextState(self, newLast: int) -> 'StairState':
        cntList = list(self.exists)
        cntList[newLast] = True
        newCnts = tuple(cntList)
        return StairState(newLast, newCnts)

    def isContainAllNums(self) -> bool:
        for c in self.exists:
            if c == 0:
                return False
        return True

def initStates() -> dict[StairState, int]:
    return {
        StairState(
            last=i,
            exists=tuple(j == i for j in range(10))
        ): 1 for i in range(1, 10)
    }

def stairNumCnt(N: int) -> int:
    states = initStates()

    for length in range(2, N+1):
        newStates: dict[StairState, int] = {}

        for state, cnt in states.items():
            nextLasts: list[int] = []
            if state.last - 1 >= 0:
                nextLasts.append(state.last - 1)
            if state.last + 1 < 10:
                nextLasts.append(state.last + 1)

            for nl in nextLasts:
                nextState = state.nextState(nl)
                if nextState in newStates:
                    newStates[nextState] += cnt
                else:
                    newStates[nextState] = cnt

        del states
        states = newStates

    totalCnt = 0
    for state, cnt in states.items():
        if state.isContainAllNums():
            totalCnt += cnt
            if totalCnt > 1_000_000_000:
                totalCnt %= 1_000_000_000

    return totalCnt


if __name__ == "__main__":
    N = int(input())


    cnt = stairNumCnt(N)

    output(f"{cnt}\n")
    flush()
