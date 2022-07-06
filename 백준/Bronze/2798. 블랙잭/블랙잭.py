sl = input().split()

n = int(sl[0])
s = int(sl[1])

sl = input().split()
nl = [int(s) for s in sl]

sum = 0
for i in range(n):
    for j in range(i+1, n):
        for k in range(j+1, n):
            sum_tmp = nl[i] + nl[j] + nl[k]
            if (sum < sum_tmp <= s):
                sum = sum_tmp
print(sum)