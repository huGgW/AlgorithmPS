from typing import *
import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def typeOfTriangle(a: int, b: int, c: int) -> str:
    if not ( abs(a - b) < c < a + b ):
        return "Invalid"

    if a == b:
        if b == c:
            return "Equilateral"
        else:
            return "Isosceles"
    elif b == c or a == c:
        return "Isosceles"
    else:
        return "Scalene"

if __name__ == "__main__":
    while True:
        a, b, c = map(int, input().split())

        if a == 0 and b == 0 and c == 0:
            break

        tp = typeOfTriangle(a, b, c)
        output(f"{tp}\n")

    flush()
