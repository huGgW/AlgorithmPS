import math

class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        for i in range(math.ceil(n / 2)):
            for j in range(math.floor(n / 2)):
                r, c = i, j
                bef = matrix[r][c]
                for _ in range(4):
                    rr, rc = self.rotateCoord(r, c, n)
                    bef, matrix[rr][rc] = matrix[rr][rc], bef
                    r, c = rr, rc


    def rotateCoord(self, r: int, c: int, n: int) -> Tuple[int, int]:
        cr, cc = (n-1) / 2, (n-1) / 2
        dr, dc = r - cr, c - cc
        drr, drc = dc, -dr
        rr, rc = drr + cr, drc + cc
        return int(rr), int(rc)
