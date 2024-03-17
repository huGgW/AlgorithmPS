class Solution:
    def integerBreak(self, n: int) -> int:
        maxMults = 0
        for spl in range(2, n+1):
            val = n // spl
            rest = n % spl
            maxValsOfSpl = [val for _ in range(spl)]
            for i in range(rest):
                maxValsOfSpl[i] += 1
        
            mult = reduce(
                lambda x, y: x * y,
                maxValsOfSpl
            )
            maxMults = max(maxMults, mult)
        
        return maxMults
