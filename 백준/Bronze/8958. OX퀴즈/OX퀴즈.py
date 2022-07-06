n = int(input())
for _ in range(n):
    result = 0
    state = 0
    s = input()
    for i in range(len(s)):
        if s[i] == 'O':
            state += 1
            result += state
        elif s[i] == 'X':
            state = 0
    print(result)