import sys
from typing import *

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def getStickerScore(stickers: List[List[int]]) -> int:
    hist = [[-1 for _ in range(n)] for _ in range(2)]

    hist[0][0] = stickers[0][0]
    hist[1][0] = stickers[1][0]

    if len(stickers[0]) > 1:
        hist[0][1] = stickers[0][1] + stickers[1][0]
        hist[1][1] = stickers[1][1] + stickers[0][0]
    
    for step in range(2, len(stickers[0])):
        hist[0][step] = max( 
            stickers[0][step] + hist[1][step-1],
            stickers[0][step] + hist[1][step-2],
        )

        hist[1][step] = max( 
            stickers[1][step] + hist[0][step-1],
            stickers[1][step] + hist[0][step-2],
        )

    return max(
        hist[0][-1],
        hist[1][-1]
    )

if __name__ == "__main__":
    tcNum = int(input())
    for _ in range(tcNum):
        n = int(input())
        stickers: List[List[int]]  = [
            list(map(int, input().split()))
            for _ in range(2)
        ]

        output(f"{getStickerScore(stickers)}\n")

    flush()
