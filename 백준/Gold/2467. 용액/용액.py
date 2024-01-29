import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

# read from input
n = int(input())
nums = list(map(int, input().split(" ")))

smallIdx, bigIdx = 0, len(nums)-1
currVal = nums[smallIdx] + nums[bigIdx]
smallestIdx, biggestIdx, zeroestVal = smallIdx, bigIdx, currVal
while bigIdx - smallIdx > 1:
    if currVal > 0:
        bigIdx -= 1
    elif currVal < 0:
        smallIdx += 1
    else:
        break

    currVal = nums[smallIdx] + nums[bigIdx]
    if abs(currVal) < abs(zeroestVal):
        smallestIdx, biggestIdx, zeroestVal = smallIdx, bigIdx, currVal

output(f"{nums[smallestIdx]} {nums[biggestIdx]}")
flush()
