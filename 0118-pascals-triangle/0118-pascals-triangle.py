class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        result = [[1]]

        for row in range(2, numRows+1):
            beforeRow = result[-1]
            row = [1] \
                + [
                    beforeRow[i] + beforeRow[i+1] \
                    for i in range(row-2)
                ] \
                + [1]
            result.append(row)

        return result
        