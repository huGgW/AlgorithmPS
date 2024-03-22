from typing import *
import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush
sys.setrecursionlimit(10**6)

n = int(input())
m = int(input())

# init graph
graph = [
    [0 if i == j else 100000*n for j in range(n+1)]
    for i in range(n+1)
]

for _ in range(m):
    f, t, c = map(int, input().split())
    graph[f][t] = min(c, graph[f][t])

# floyd warshall
for k in range(1, n+1):
    for a in range(1, n+1):
        for b in range(1, n+1):
            graph[a][b] = min(
                graph[a][b],
                graph[a][k] + graph[k][b]
            )

for i in range(1, n+1):
    for j in range(1, n+1):
        if graph[i][j] == 100000*n:
            graph[i][j] = 0

for i in range(1, n+1):
    for j in range(1, n+1):
        output(f"{graph[i][j]} ")
    output("\n")

flush()

