from math import factorial

m: int = int(input())
n: int = factorial(m)

cnt2: int = 0
cnt5: int = 0

while (n % 5 == 0):
    n //= 5
    cnt5 += 1
while (n % 2 == 0):
    n //= 2
    cnt2 += 1

print(min(cnt2, cnt5));