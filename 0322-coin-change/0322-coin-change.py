from typing import *
from bisect import *
import sys
sys.setrecursionlimit(10**6)

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        coins.sort()
        memo: Dict[int, Optional[int]] = {}
        result = self.lookUpCoinChangeRecursive(coins, amount, memo)
        return -1 if result is None else result

    def lookUpCoinChangeRecursive(
        self, coins: List[int], amount: int, memo: Dict[int, Optional[int]]
    ) -> Optional[int]:
        if amount < 0:
            return None
        elif amount == 0:
            return 0

        if amount in memo:
            return memo[amount]

        childResult = []
        for c in coins:
            if c <= amount:
                result = self.lookUpCoinChangeRecursive(coins, amount - c, memo)
                if result is not None:
                    childResult.append(result + 1)
            else:
                break

        result = min(childResult) if childResult else None
        memo[amount] = result
        return result
            


