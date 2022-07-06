n = 0
x = int(input())

max = 0
while(x > max):
    n += 1
    max += n

if (n % 2 == 1):
    son = 1 + (max - x)
    mother = n - (max - x)
else:
    mother = 1 + (max - x)
    son = n - (max - x)


print(son, "/", mother, sep="")