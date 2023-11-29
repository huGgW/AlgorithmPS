import sys

input = sys.stdin.readline
print = sys.stdout.write

n, m = map(int, input().split())
ground = []

dx, dy = -1, -1

for i in range(n):
    row = list(map(int, input().split()))
    ground.append(row)
    for j in range(m):
        if row[j] == 2:
            dx, dy = i, j

dist = [[0 if ground[i][j] == 0 else -1 for j in range(m)] for i in range(n)]

queue = [(dx, dy, 0)]
dist[dx][dy] = 0
step = 1
while queue:
    x, y, d = queue.pop(0)
    # print(f"\t{(x, y, d)}\n")
    for nx, ny in [
        (x-1, y),
        (x+1, y),
        (x, y-1),
        (x, y+1),
    ]:
        # range check
        if not (0 <= nx < n and 0 <= ny < m):
            continue

        # not proceed to wall
        if ground[nx][ny] == 0:
            continue

        orig_d = dist[nx][ny]
        if orig_d == -1 or d+1 < orig_d:
            dist[nx][ny] = d+1
            queue.append((nx, ny, d+1))

for row in dist:
    for d in row:
        print(f"{d} ")
    print("\n")

sys.stdout.flush()
