import sys

n, m = map(int, sys.stdin.readline().rstrip().split())

# candies
maze = [[0 for _ in range(m+1)] for _ in range(n+1)]
for i in range(1, n+1):
    maze[i][1:m+1] = map(int, sys.stdin.readline().rstrip().split())

# fill TABLE of dynamic programming
maxCandy = [[0 for _ in range(m+1)] for _ in range(n+1)]
for i in range(1, n+1):
    for j in range(1, m+1):
        maxCandy[i][j] = max(
            maxCandy[i-1][j],
            maxCandy[i][j-1],
            maxCandy[i-1][j-1]
        ) + maze[i][j]

# print result
print(maxCandy[n][m])