class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        maxArea = 0
        colContOnes = [0] * len(matrix[0])

        for (i, row) in enumerate(matrix):
            # count ones
            for j in range(len(colContOnes)):
                # no info: recount
                if colContOnes[j] == 0:
                    cnt = 0
                    for ri in range(i, len(matrix)):
                        if matrix[ri][j] == '1':
                            cnt += 1
                        else:
                            break
                    colContOnes[j] = cnt
                # has info: reduce 1
                else:
                    colContOnes[j] -= 1

            # calc max rect begin from j
            canIgnore = [False] * len(colContOnes)
            for j in range(len(colContOnes)):
                minHeight = colContOnes[j]
                for jj in range(j, len(colContOnes)):
                    if colContOnes[jj] == 0 or canIgnore[jj]:
                        break
                    minHeight = min(colContOnes[jj], minHeight)
                    maxArea = max((jj - j + 1) * minHeight, maxArea)

                    if minHeight == colContOnes[jj]:
                        canIgnore[jj] = True

        return maxArea

        