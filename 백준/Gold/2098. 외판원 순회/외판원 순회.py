from typing import *
import math
import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline

def main():
    # Get graph from input
    N = int(input())
    G: List[List[int]] = []
    for _ in range(N):
        G.append(list(map(int, input().split())))

    dp: Dict[Tuple[int, int], Union[int, float]] = {}
    dfs(0, 1, dp, N, G)
    # print(dp)
    print(dp[(0, 1)])

def dfs(city: int, s: int, dp: Dict[Tuple[int, int], Union[int, float]], N: int, G: List[List[int]]):
    if (city, s) in dp:
        return

    if setFull(s, N):
        if G[city][0] != 0:
            dp[(city, s)] = G[city][0]
        else:
            dp[(city, s)] = math.inf
        return

    minVal = math.inf
    for nextCity in range(N):
        if G[city][nextCity] == 0 or contains(s, nextCity):
            continue

        nextSet = setBit(s, nextCity)
        dfs(nextCity, nextSet, dp, N, G)
        val = dp.get((nextCity, nextSet), math.inf) + G[city][nextCity]

        if val < minVal:
            minVal = val

    dp[(city, s)] = minVal


    



def bitOf(idx: int) -> int:
    return 1 << idx

def setBit(s: int, idx: int) -> int:
    return s | bitOf(idx)

def unsetBit(s: int, idx: int) -> int:
    return s & ~bitOf(idx)

def contains(s: int, idx: int) -> bool:
    return bool(s & bitOf(idx))

def setFull(s: int, n: int) -> bool:
    return ((1 << n) - 1) ^ s == 0


if __name__ == "__main__":
    main()
