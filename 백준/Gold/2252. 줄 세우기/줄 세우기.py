from typing import *
import sys
input = sys.stdin.readline

N, M = map(int, input().split(" "))

# init children
children: Dict[int, List[int]] = {}
cnt: Dict[int, int] = {}
for _ in range(M):
    f, b = map(int, input().split(" "))
    if f not in children:
        children[f] = []
    children[f].append(b)
    cnt[b] = cnt.get(b, 0) + 1

# traverse DAG
roots = set(i for i in range(1, N+1)).difference(cnt.keys())
ls = []
for root in roots:
    queue = [root]
    while queue:
        node = queue.pop(0)
        ls.append(node)
        for child in children.get(node, []):
            cnt[child] -= 1
            if cnt[child] == 0:
                queue.append(child)

print(''.join(
    map(lambda n: f"{n} ", ls)
))