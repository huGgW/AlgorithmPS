class Solution:
    def integerBreak(self, n: int) -> int:
        # maxMults = 0
        # for spl in range(2, n+1):
        #     val = n // spl
        #     rest = n % spl
        #     maxValsOfSpl = [val for _ in range(spl)]
        #     for i in range(rest):
        #         maxValsOfSpl[i] += 1
        #
        #     mult = reduce(
        #         lambda x, y: x * y,
        #         maxValsOfSpl
        #     )
        #     maxMults = max(maxMults, mult)
        #
        # return maxMults

        hist = [0, 1, 1]
        for h in range(3, n+1):
            maxMult = 0
            for x in range(1, h-2):
                y = h - x
                xMultMax = max(x, hist[x])
                yMultMax = max(y, hist[y])
                mult = xMultMax * yMultMax
                maxMult = max(mult, maxMult)
            hist.append(maxMult)

        return hist[-1]
