import sys
from functools import reduce

input = sys.stdin.readline
output = sys.stdout.write

n = int(input())
edges = {}
for i in range(1, n+1):
    edges[i] = set()

for _ in range(n-1):
    x, y = map(int, input().split())
    edges[x].add(y)
    edges[y].add(x)

parent = [0 for _ in range(n+1)]
queue = [1]
while queue:
    p = queue.pop()
    for x in edges[p]:
        queue.append(x)
        parent[x] = p
        edges[x].remove(p)
    edges[p] = set()

output(reduce(lambda x, y: f"{x}\n{y}", parent[2:]))
sys.stdout.flush()
