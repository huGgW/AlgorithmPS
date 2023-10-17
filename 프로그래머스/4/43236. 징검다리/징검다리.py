from typing import List
from math import ceil

def solution(distance: int, rocks: List[int], n: int) -> int:
    if n == len(rocks):
        return distance

    rocks.sort()
    rocks.insert(0, 0)
    rocks.append(distance)

    return binarySearch(rocks, 0, ceil(distance / (len(rocks)-1-n)), n, 0)


def binarySearch(rocks: List[int], b: int, e: int, n: int, mx: int) -> int: # mx: 지금까지 결과 중 최대값 저장
    if b > e:
        return mx

    m = (b + e) // 2
    sim = simulate(rocks, m, n)

    if sim < 0:
        return binarySearch(rocks, b, m-1, n, mx)
    else:
        return binarySearch(rocks, m+1, e, n, max(m, mx)) # max(m, mx)로 결과 중 최댓값 전달


# threshold  이상 간격이 되도록 돌의 개수 제거 (n에서 뺴는 방식으로)
def simulate(rocks: List[int], threshold: int, n: int) -> int:
    i = 0
    for j in range(1, len(rocks)-1):
        diff = rocks[j] - rocks[i]
        if diff >= threshold:
            i = j
        else:
            n -= 1
    if rocks[-1] - rocks[i] < threshold:
        n -= 1

    return n
