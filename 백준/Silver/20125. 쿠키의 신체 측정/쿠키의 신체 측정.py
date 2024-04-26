import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

N = int(input())
coord = [
    list(input().strip())
    for _ in range(N)
]

# find heart
heartI, heartJ = -1, -1
for i in range(1, N-1):
    for j in range(1, N-1):
        if coord[i][j] == '*' \
        and coord[i-1][j] == '*' \
        and coord[i+1][j] == '*' \
        and coord[i][j-1] == '*' \
        and coord[i][j+1] == '*':
            heartI, heartJ = i, j
            break

# left arm
leftArmEnd = heartJ-1
while True:
    if leftArmEnd > 0 and coord[heartI][leftArmEnd-1] == '*':
        leftArmEnd -= 1
    else:
        break

# right arm
rightArmEnd = heartJ+1
while True:
    if rightArmEnd < N-1 and coord[heartI][rightArmEnd+1] == '*':
        rightArmEnd += 1
    else:
        break

# back
backEnd = heartI+1
while True:
    if coord[backEnd+1][heartJ] == '*':
        backEnd += 1
    else:
        break

# left leg
leftLegEnd = backEnd+1
while True:
    if leftLegEnd < N-1 and coord[leftLegEnd+1][heartJ-1] == '*':
        leftLegEnd += 1
    else:
        break

# right leg
rightLegEnd = backEnd+1
while True:
    if rightLegEnd < N-1 and coord[rightLegEnd+1][heartJ+1] == '*':
        rightLegEnd += 1
    else:
        break

output(f"{heartI+1} {heartJ+1} \n")
output(f"{heartJ - leftArmEnd} ")
output(f"{rightArmEnd - heartJ} ")
output(f"{backEnd - heartI} ")
output(f"{leftLegEnd - backEnd} ")
output(f"{rightLegEnd - backEnd}\n")

flush()
