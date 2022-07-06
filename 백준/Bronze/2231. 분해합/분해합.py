def disSum(n: int) -> int:
    sum: int = n
    while (n != 0):
        sum += (n % 10)
        n //= 10
    return sum

n: int = int(input())
constructor: int = 0

for i in range(1, n):
    ds: int = disSum(i)
    if (ds == n):
        constructor = i
        break

print(constructor)