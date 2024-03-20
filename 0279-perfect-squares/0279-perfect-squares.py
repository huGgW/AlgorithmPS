import math

class Solution:
    def numSquares(self, n: int) -> int:
        return self.lookupNumSquares(
            n, self.largestPossibleSquareNum(n), {}
        )
    
    def lookupNumSquares(
        self, n: int, sq: int, memo: Dict[Tuple[int, int], int]
    ) -> int:

        if (n, sq) in memo:
            return memo[(n, sq)]

        if 0 <= n <= 1 or sq == 1:
            return n
            

        minCnt = n
        for i in range(n // (sq**2), -1, -1):
            residual = n - (sq * sq * i)
            targetSq = min(self.largestPossibleSquareNum(residual), sq-1)
            cnt = i + self.lookupNumSquares(residual, targetSq, memo)
            if cnt <= minCnt:
                minCnt = cnt
            else:
                break

        memo[(n, sq)] = minCnt
        return minCnt

    def largestPossibleSquareNum(self, x: int) -> int:
        return math.floor(x ** 0.5)