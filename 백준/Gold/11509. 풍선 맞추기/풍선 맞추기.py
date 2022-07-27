import sys

# initialization
n: int = int(sys.stdin.readline())
nums: list[int] = list(map(int, sys.stdin.readline().split(" ")))
arrows: list[int] = []

# for all balloons, if there's no arrow of balloon's height then append with h-1, 
# else sub 1 to that arrow's height
for x in nums:
    hit: bool = False
    for i in range(len(arrows)):
        if x == arrows[i]:
            arrows[i] -= 1
            hit = True
            break
    if not arrows or not hit:
        arrows.append(x-1)

sys.stdout.write(str(len(arrows)))