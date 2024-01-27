import sys
input = sys.stdin.readline

n = int(input())
area = 0
cx, cy = map(int, input().split())
bx, by = map(int, input().split())
vbx, vby = bx-cx, by-cy
for i in range(2, n):
    x, y = map(int, input().split())
    vx, vy = x - cx, y - cy
    area += vbx * vy - vby * vx
    vbx, vby = vx, vy

print(f"{abs(area/2):.1f}")