from typing import List
import math

sl: List[str] = input().split(" ")

n: int = int(sl[0])
k: int = int(sl[1])

result: int = math.factorial(n) // (math.factorial(k) * math.factorial(n - k))

print(result)