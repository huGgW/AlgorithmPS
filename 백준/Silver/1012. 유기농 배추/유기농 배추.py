import sys

t = int(sys.stdin.readline().rstrip())
bugcnts: list[int] = []

for _ in range(t):
    m, n, k = map(
        int,
        sys.stdin.readline().split()
    )

    baechoobat = [[0 for _ in range(m)] for _ in range(n)]
    baechoocoord: list[tuple[int,int]] = []
    bugcnt = 0

    for _ in range(k):
        x, y = map(int, sys.stdin.readline().split())
        baechoobat[y][x] = 1
        baechoocoord.append((y, x))

    for (i, j) in baechoocoord:
        if baechoobat[i][j] == 1:
            bugcnt += 1
            # BFS
            queue = [(i, j)]
            while queue:
                ii, jj = queue.pop(0)
                if baechoobat[ii][jj] != 0:
                    baechoobat[ii][jj] = 0
                    if ii >= 1 and baechoobat[ii-1][jj] == 1:
                        queue.append((ii-1, jj))
                    if ii < n-1 and baechoobat[ii+1][jj] == 1:
                        queue.append((ii+1, jj))
                    if jj >= 1 and baechoobat[ii][jj-1] == 1:
                        queue.append((ii, jj-1))
                    if jj < m-1 and baechoobat[ii][jj+1] == 1:
                        queue.append((ii, jj+1))
    
    bugcnts.append(bugcnt)

for cnt in bugcnts:
    sys.stdout.write(str(cnt) + "\n")
                