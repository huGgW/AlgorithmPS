a = int(input())
b = int(input())
c = int(input())
nL = [0 for i in range(10)]
x = a*b*c
while x > 0:
    d = x % 10
    x = x // 10
    nL[d]+=1
for cnt in nL:
    print(cnt)