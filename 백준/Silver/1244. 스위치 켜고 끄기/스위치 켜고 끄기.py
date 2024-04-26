import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

N = int(input())
switches = [s == '1' for s in input().split()]
S = int(input())

for _ in range(S):
    gender, c = map(int, input().split())

    if gender == 1: # male
        for i in range(c-1, N, c):
            switches[i] = not switches[i]
    else: # female
        switches[c-1] = not switches[c-1]

        left, right = c-2, c
        while left >= 0 and right < N:
            if switches[left] == switches[right]:
                switches[left] = not switches[left]
                switches[right] = not switches[right]

                left -= 1
                right += 1
            else:
                break

for (i, s) in enumerate(switches):
    output(f"{1 if s else 0}")
    if i != N-1 and (i+1) % 20 == 0:
        output('\n')
    elif i != N-1:
        output(' ')
flush()
