from typing import List
import sys

n: int = int(sys.stdin.readline().rstrip())

honey: List[int] = list(map(int, sys.stdin.readline().split()))
honey.insert(0, 0)

honeySums: List[int] = honey.copy()
for i in range(1, n+1):
    honeySums[i] += honeySums[i-1]

# 1. maximum value of one bee left, one bee right
resultLR: int = 0
# h : index of bultong
for h in range(1, n+1):
    tmp: int = 0
    # 1. one on left, one on right
    if (2 <= h <= n-1):
        tmp += (honeySums[h] - honey[1]) # Left
        tmp += (honeySums[n-1] - honeySums[h-1]) # Right
    resultLR = tmp if tmp > resultLR else resultLR

# 2. maximum value of bees left, left
resultLL: int = 0
resultLL += honeySums[n] - honey[1] # one bee on leftmost
delta: int = 0 # delta value by adding second bee
for i in range(2, n): # second bee
    tmp: int = honeySums[n] - honeySums[i] - honey[i]
    delta = tmp if tmp > delta else delta
resultLL += delta

# 3. maximum value of bees right, right
resultRR: int = 0
resultRR += honeySums[n-1] # one bee on rightmost
delta: int = 0 # delta value by adding second bee
for i in range(n-1, 1, -1): # second bee
    tmp: int = honeySums[i-1] - honey[i]
    delta = tmp if tmp > delta else delta
resultRR += delta

print(max(resultLR, resultLL, resultRR))