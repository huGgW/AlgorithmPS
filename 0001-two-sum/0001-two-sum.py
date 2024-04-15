class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        indexed = sorted(
            enumerate(nums),
            key=lambda t: t[1]
        )

        highEnd = None
        for i in range(len(indexed)):
            lowNum = indexed[i][1]
            highNeedNum = target - lowNum
            bisectIdx = self.bisectLeftWithKey(
                indexed, highNeedNum,i+1, highEnd, 
                key=lambda t: t[1]
            )


            if bisectIdx < len(indexed) \
            and indexed[bisectIdx][1] == highNeedNum:
                return [indexed[i][0], indexed[bisectIdx][0]]
            
            highEnd = min(bisectIdx, len(indexed)-1)

        raise Exception

    def bisectLeftWithKey(
        self,
        ls: List,
        x,
        lo: int = 0,
        hi: Optional[int] = None,
        key = lambda x: x,
    ) -> int:
        if hi is None:
            hi = len(ls) - 1

        if lo > hi:
            return lo

        mid = int((lo + hi) // 2)
        midVal = key(ls[mid])

        if x <= midVal:
            return self.bisectLeftWithKey(
                ls, x, lo, mid-1, key
            )
        elif x > midVal:
            return self.bisectLeftWithKey(
                ls, x, mid+1, hi, key
            )

