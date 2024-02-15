from typing import *
from dataclasses import dataclass
import sys

sys.setrecursionlimit(10**6)


@dataclass
class BracketStat:
    openCnt: int
    closeCnt: int

    def isPossible(self, remains: int) -> bool:
        return self.openCnt >= self.closeCnt and self.openCnt <= self.closeCnt + remains

    def isDonePossible(self) -> bool:
        return self.openCnt == self.closeCnt


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        availableList = self._combinationParens(n * 2, 0, [], BracketStat(0, 0))
        return list(map(lambda ls: "".join(ls), availableList))

    def _combinationParens(
        self, numElem: int, idx: int, tmp: List[str], stat: BracketStat
    ) -> List[List[str]]:
        if not stat.isPossible(numElem - len(tmp)):
            return []

        if idx == numElem:
            return [tmp.copy()] if stat.isDonePossible() else []

        result = []

        # (
        tmp.append("(")
        stat.openCnt += 1
        result += self._combinationParens(numElem, idx + 1, tmp, stat)
        stat.openCnt -= 1
        tmp.pop(-1)

        # )
        tmp.append(")")
        stat.closeCnt += 1
        result += self._combinationParens(numElem, idx + 1, tmp, stat)
        stat.closeCnt -= 1
        tmp.pop(-1)

        return result
