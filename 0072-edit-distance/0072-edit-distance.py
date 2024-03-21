from typing import *
import sys
sys.setrecursionlimit(10**6)

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        memo: Dict[Tuple[str, str], int] = {}
        result = self.lookUpMinDistance(word1, word2, memo)
        # pprint(memo)
        return result

    def lookUpMinDistance(
        self, word1: str, word2: str, memo: Dict[Tuple[str, str], int]
    ) -> int:
        # lookup memo
        if (word1, word2) in memo:
            return memo[(word1, word2)]

        # base conditions
        if word1 == word2:
            memo[(word1, word2)] = 0
            return 0

        if len(word1) == 0:
            memo[(word1, word2)] = len(word2)
            return len(word2)
        elif len(word2) == 0:
            memo[(word1, word2)] = len(word1)
            return len(word1)


        if word1[0] == word2[0]:
            dist = self.lookUpMinDistance(word1[1:], word2[1:], memo)
            memo[(word1, word2)] = dist
            return dist

        removeDist = 1 + self.lookUpMinDistance(word1[1:], word2, memo)
        addDist = 1 + self.lookUpMinDistance(word1, word2[1:], memo)
        replaceDist = 1 + self.lookUpMinDistance(word1[1:], word2[1:], memo) 

        minDist = min(removeDist, addDist, replaceDist)
        memo[(word1, word2)] = minDist
        return minDist
