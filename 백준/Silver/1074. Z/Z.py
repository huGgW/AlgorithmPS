import sys

input = sys.stdin.readline
print = sys.stdout.write

n, r, c = map(int, input().split())
nth = 0

k = 2**n
for _ in range(n):
    half = int(k // 2)
    mult = 0

    if 0 <= r < half and 0 <= c < half:
        mult = 0
    elif 0 <= r < half and half <= c < k:
        c -= half
        mult = 1
    elif half <= r < k and 0 <= c < half:
        r -= half
        mult = 2
    else:
        r -= half; c-= half
        mult = 3

    nth += mult * half**2
    k /= 2

print(str(nth) + "\n")
sys.stdout.flush()
