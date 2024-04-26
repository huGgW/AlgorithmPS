from bisect import bisect_left, bisect_right
import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

N, p, maxCnt = map(int, input().split())
if N == 0:
    print(1)
    exit()

points = [-1 * int(s) for s in input().split()]

# sort points
points.sort()

# find p can be in maxCnt
rightBisect = bisect_right(points, -p)
if rightBisect >= maxCnt:
    print(-1)
    exit()

leftBisect = bisect_left(points, -p, hi=rightBisect)
print(leftBisect+1)
