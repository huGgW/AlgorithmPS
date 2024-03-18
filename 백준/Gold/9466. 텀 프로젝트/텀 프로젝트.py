import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

from typing import *
from enum import Enum, auto
# from pprint import pprint

class State(Enum):
    READY = auto()
    VISITED = auto()
    LOOP = auto()
    ISOLATED = auto()



def main(nextStud: List[int], n: int) -> int:
    states = [State.READY for _ in range(n+1)]
    cnt = 0

    for i in range(1, n+1):
        # pprint(states)

        if states[i] is not State.READY:
            continue

        # mark visited
        j = i 
        while states[j] == State.READY:
            states[j] = State.VISITED
            j = nextStud[j]

        # mark loop
        k = j
        while states[k] != State.LOOP and states[k] != State.ISOLATED:
            states[k] = State.LOOP
            k = nextStud[k]

        # mark isolated
        s = i
        while states[s] != State.LOOP and states[s] != State.ISOLATED:
            states[s] = State.ISOLATED
            cnt += 1
            s = nextStud[s]

    return cnt


if __name__ == "__main__":
    n_tc = int(input())
    for _ in range(n_tc):
        n = int(input())
        vals = [0] + [int(x) for x in input().split()]
        result = main(vals, n)
        output(f"{result}\n")

    flush()