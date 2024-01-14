from typing import *
from sys import exit

N, K = map(int, input().split())

# if N is forward of K
if N >= K:
    print(N-K)
    exit()

queue: List[Tuple[int, int]] = [(N, 0)]
hist: Dict[int, int] = {N: 0}

while queue:
    p, t = queue.pop(0)

    # debug
    # print(p, t)

    if p >= K:
        if hist.get(K, t + (p-K) + 1) > t + (p-K):
            hist[K] = t + (p-K)
        continue

    # go forward
    if hist.get(p+1, t+2) > t+1:
        hist[p+1] = t+1
        queue.append((p+1, t+1))

    # go backward
    if p-1 > 0 and hist.get(p-1, t+2) > t+1:
        hist[p-1] = t+1
        queue.append((p-1, t+1))

    # jump twice
    if hist.get(p*2, t+1) > t:
        hist[p*2] = t
        queue.append((p*2, t))

print(hist[K])
