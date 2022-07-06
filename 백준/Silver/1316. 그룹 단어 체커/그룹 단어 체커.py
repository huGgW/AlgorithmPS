n: int = int(input())

cnt: int = 0

for i in range(n):
    isGroupWord: bool = True
    before: str = None
    occured: set = set()
    s: str = input()
    for c in s:
        if (before != c):
            if (c in occured):
                isGroupWord = False
                break
            else:
                before = c
                occured.add(c)
    if (isGroupWord): cnt += 1

print(cnt)