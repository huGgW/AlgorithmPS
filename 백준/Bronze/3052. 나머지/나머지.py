rest = set()
for i in range(10):
    n = int(input())
    rest.add(n % 42)
print(len(rest))