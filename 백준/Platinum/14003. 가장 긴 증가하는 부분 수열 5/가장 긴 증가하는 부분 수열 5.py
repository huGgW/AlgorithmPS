from typing import *
from bisect import bisect_left
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def LIS(N: int, nums: List[int]) -> Tuple[int, List[int]]:
    lisPossible = []
    insertedIdxs = [-1] * N

    # analyze LIS length
    # 추가적으로 삽입때마다 idx를 기록해서 추후 실제 LIS를 생성하는데 활용
    for (i, n) in enumerate(nums):
        # 원소가 lisPossible 모두보다 더 크다면 새로 추가
        if not lisPossible or n > lisPossible[-1]:
            lisPossible.append(n)
            insertedIdxs[i] = len(lisPossible)-1
        # 순서를 지키며 더 작게 만들수 있는 원소를 작게 만듬
        else:
            idx = bisect_left(lisPossible, n)
            lisPossible[idx] = n
            insertedIdxs[i] = idx

    # lis의 뒤부터 실제 원소를 채워나감.
    # 맨 뒤의 숫자부터 lisPossible에 삽입된 idx를 체크, lis에 적합한 위치면 이를 삽입
    # 맨 뒤부터 시행해야 마지막으로 추가된 원소까지의 상태를 체크 가능하다.
    ln = len(lisPossible)
    lis = [-1 for _ in range(ln)]
    lisIdx = ln - 1
    for i in range(N-1, -1, -1):
        if lisIdx < 0:
            break

        if insertedIdxs[i] == lisIdx:
            lis[lisIdx] = nums[i]
            lisIdx -= 1

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
