import math

from typing import *
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def main(vals: List[int], N: int) -> Tuple[int, int, int]:
    vals.sort()

    absSum = math.inf
    state: Tuple[int, int, int] = (-1, -1, -1)

    for i in range(N-2):
        j, k = i+1, N-1
        while k - j > 0:
            sumOf = vals[i] + vals[j] + vals[k]
            if abs(sumOf) < absSum:
                absSum = abs(sumOf)
                state = (vals[i], vals[j], vals[k])

                if sumOf == 0:
                    return state

            if sumOf < 0:
                j += 1
            else:
                k -= 1

    return state



if __name__ == "__main__":
    N = int(input())
    vals = [int(x) for x in input().split()]

    a, b, c = main(vals, N)
    output(f"{a} {b} {c}")
    flush()
