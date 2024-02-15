from typing import *
import sys
sys.setrecursionlimit(2**31-1)

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        self.candidates = candidates
        self.candidates.sort()

        result = []
        self._combinationList([], 0, [target], result)

        return result

    def _combinationList(self, tmp: List[int], limit: int, tPtr: List[int], result: List[List[int]]):
        if tPtr[0] == 0:
            result.append(tmp.copy())
            return
        elif tPtr[0] < 0:
            return

        for i in range(limit, len(self.candidates)):
            val = self.candidates[i]
            tmp.append(val)
            tPtr[0] -= val
            self._combinationList(tmp, i, tPtr, result)
            tPtr[0] += val
            tmp.pop(-1)
