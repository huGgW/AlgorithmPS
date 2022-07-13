def fib(n, cnt):
    if (n == 1 or n == 2):
        cnt[0] += 1
        return 1
    else: return (fib(n-1, cnt) + fib(n-2, cnt))

def fibonacci(n, cnt):
    f = [0, 1, 1]
    for i in range(3, n+1):
        cnt[1] += 1
        f.append(f[i-1] + f[i-2])
    return f[n]

cnt = [0, 0]
n = int(input())

fib(n, cnt)
fibonacci(n, cnt)

print(cnt[0], cnt[1])