class Solution:
    def integerBreak(self, n: int) -> int:
        maxMults = 0
        for spl in range(2, n+1):
            val = n // spl
            rest = n % spl

            mult = 1
            for i in range(spl):
                mult *= val + 1 if i < rest else val

            maxMults = max(maxMults, mult)

        return maxMults
