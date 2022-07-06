from typing import List
from math import floor
s: str = input()
sSplited: List[str] = s.split()
A: int = int(sSplited[0])
B: int = int(sSplited[1])
C: int = int(sSplited[2])

if (C - B > 0):
    result: int = floor(A / (C - B)) + 1
else:
    result: int = -1

print(result)