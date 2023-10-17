from typing import List
from math import ceil

def solution(distance: int, rocks: List[int], n: int) -> int:
    if n == len(rocks):
        return distance

    rocks.sort()
    rocks.insert(0, 0)
    rocks.append(distance)

    return binarySearch(rocks, 0, ceil(distance / len(rocks)), n, 0)


def binarySearch(rocks: List[int], b: int, e: int, n: int, mx: int) -> int:
    print(f"b: {b}, e: {e}, max: {mx}")
    if b > e:
        return mx

    m = (b + e) // 2
    sim = simulate(rocks, m, n)

    if sim < 0:
        return binarySearch(rocks, b, m-1, n, mx)
    else:
        return binarySearch(rocks, m+1, e, n, max(m, mx))


def simulate(rocks: List[int], threshold: int, n: int) -> int:
    i = 0
    for j in range(1, len(rocks)-1):
        diff = rocks[j] - rocks[i]
        if diff >= threshold:
            i = j
        else:
            n -= 1
    if rocks[len(rocks)-1] - rocks[i] < threshold:
        n -= 1

    return n
