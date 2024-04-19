from typing import *
from bisect import bisect_left
from collections import deque
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def LIS(N: int, nums: List[int]) -> Tuple[int, List[int]]:
    lisPossible = []
    lisInsertOrder = []

    # analyze LIS length
    # 추가적으로 삽입때마다 idx를 기록해서 추후 실제 LIS를 생성하는데 활용
    for (i, n) in enumerate(nums):
        # 원소가 lisPossible 모두보다 더 크다면 새로 추가
        if not lisPossible or n > lisPossible[-1]:
            lisPossible.append(n)
            lisInsertOrder.append(deque([i]))
        # 순서를 지키며 더 작게 만들수 있는 원소를 작게 만듬
        else:
            idx = bisect_left(lisPossible, n)
            lisPossible[idx] = n
            lisInsertOrder[idx].append(i)

    # lisInsertOrder를 이용하여 뒤에서부터 가능한 큰 index를 가진 insert된 원소들을 lis에 삽입
    ln = len(lisPossible)
    lis = [-1 for _ in range(ln)]
    for i in range(ln-1, -1, -1):
        if i == ln-1:
            lis[i] = nums[lisInsertOrder[i][-1]]
        else:
            lim = lisInsertOrder[i+1][-1]
            while lisInsertOrder[i][-1] >= lim:
                lisInsertOrder[i].pop()
            lis[i] = nums[lisInsertOrder[i][-1]]

    return ln, lis



if __name__ == "__main__":
    N = int(input())
    nums = [int(s) for s in input().split()]

    length, lis = LIS(N, nums)
    output(f"{length}\n")
    for x in lis:
        output(f"{x} ")
    output("\n")

    flush()
