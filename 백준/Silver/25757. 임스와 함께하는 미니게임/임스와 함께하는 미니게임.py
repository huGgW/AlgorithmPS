from typing import *
import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def needCnt(game):
    if game == 'Y':
        return 1
    elif game == 'F':
        return 2
    elif game == 'O':
        return 3

nstr, game = input().split()
N = int(nstr)
shouldCnt = needCnt(game)
played = set()

cnt = 0
gameCnt = 0
for _ in range(N):
    pep = input()
    if pep in played:
        continue

    cnt += 1
    played.add(pep)
    if cnt == shouldCnt:
        cnt = 0
        gameCnt += 1

print(gameCnt)
