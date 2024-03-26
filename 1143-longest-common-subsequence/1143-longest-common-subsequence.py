class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        memo = [[0 for _ in range(len(text2))] for _ in range(len(text1))]

        for i in range(len(text1)):
            for j in range(len(text2)):
                if text1[i] == text2[j]:
                    upleft = memo[i-1][j-1] \
                        if 1 <= i and 1 <= j \
                        else 0
                    memo[i][j] = upleft+1

                else:
                    up = memo[i-1][j] \
                        if 1 <= i \
                        else 0

                    left = memo[i][j-1] \
                        if 1 <= j \
                        else 0

                    memo[i][j] = max(up, left)

        return memo[-1][-1]

