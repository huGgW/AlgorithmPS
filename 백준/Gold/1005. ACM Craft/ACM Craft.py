import math
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

test_cnt = int(input())

for tc in range(test_cnt):
    # read input
    N, K = map(int, input().split())
    Ds = [0] + list(map(int, input().split()))

    graph = [[] for _ in range(N+1)]
    cnt = [0 for _ in range(N+1)]
    time = [0 for _ in range(N+1)]

    # read graph
    for _ in range(K):
        f, t = map(int, input().split())
        graph[f].append(t)
        cnt[t] += 1

    # read W
    W = int(input())

    # traverse DAG
    queue = list(filter(lambda i: cnt[i] == 0, range(1, N+1)))
    while queue:
        next = queue.pop(0)
        time[next] += Ds[next]

        nextBuilds = graph[next]
        for nextBuild in nextBuilds:
            cnt[nextBuild] -= 1
            time[nextBuild] = max(time[nextBuild], time[next])
            if cnt[nextBuild] == 0:
                queue.append(nextBuild)

    output(str(time[W]) + "\n")

flush()
