m = int(input())

for i in range(m):
    l = input().split()
    h = int(l[0])
    w = int(l[1])
    n = int(l[2])
    x = ((n-1) % h) + 1
    y = ((n-1) // h) + 1
    print("{}{:02d}".format(x, y))