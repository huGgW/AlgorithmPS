class Solution:
    def climbStairs(self, n: int) -> int:
        hist = [None for _ in range(n+1)]

        if n <= 2:
            return n
        
        bb = 1
        b = 2
        for _ in range(3, n+1):
            c = bb + b
            bb = b
            b = c

        return b