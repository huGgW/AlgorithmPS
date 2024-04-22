from enum import Enum
class Dir(Enum):
    RIGHT = 0
    DOWN = 1
    LEFT = 2
    UP = 3

    @staticmethod
    def nextDir(dir: "Dir") -> "Dir":
        nextValue = (dir.value + 1) % 4
        return Dir(nextValue)


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        ls = []

        lowBoundRow, highBoundRow = -1, len(matrix)
        lowBoundCol, highBoundCol = -1, len(matrix[0])
        r, c = 0, -1
        dir = Dir.RIGHT

        while self.nextAvailable(
            r, c, dir, lowBoundRow, highBoundRow, lowBoundCol, highBoundCol
        ):
            r, c = self.dirGoToEnd(
                matrix,
                lowBoundRow, highBoundRow,
                lowBoundCol, highBoundCol,
                r, c, dir,
                ls
            ) 
            dir = Dir.nextDir(dir)
            lowBoundRow, highBoundRow, lowBoundCol, highBoundCol = self.updateBoundary(
                r, c, dir, lowBoundRow, highBoundRow, lowBoundCol, highBoundCol
            )

        return ls

    def nextAvailable(
        self, r: int, c: int, dir: int,
        lowBoundRow: int, highBoundRow: int,
        lowBoundCol: int, highBoundCol: int,
    ) -> bool:
        if dir == Dir.RIGHT:
            return c + 1 < highBoundCol
        elif dir == Dir.LEFT:
            return c - 1 > lowBoundCol
        elif dir == Dir.DOWN:
            return r + 1 < highBoundRow
        else: # UP
            return r - 1 > lowBoundRow

    def dirGoToEnd(
        self, matrix: List[List[int]],
        lowBoundRow: int, highBoundRow: int,
        lowBoundCol: int, highBoundCol: int,
        r: int, c: int, dir: Dir,
        ls: List[int]
    ) -> Tuple[int, int]:
        if dir == Dir.RIGHT:
            while c + 1 < highBoundCol:
                c += 1
                ls.append(matrix[r][c])

        elif dir == Dir.LEFT:
            while c - 1 > lowBoundCol:
                c -= 1
                ls.append(matrix[r][c])

        elif dir == Dir.DOWN:
            while r + 1 < highBoundRow:
                r += 1
                ls.append(matrix[r][c])

        else: # UP
            while r - 1 > lowBoundRow:
                r -= 1
                ls.append(matrix[r][c])

        return r, c

    def updateBoundary(
        self, r: int, c: int, dir: Dir,
        lowBoundRow: int, highBoundRow: int,
        lowBoundCol: int, highBoundCol: int,
    ) -> Tuple[int, int, int, int]:
        if dir == Dir.RIGHT:
            lowBoundCol = c

        elif dir == Dir.LEFT:
            highBoundCol = c

        elif dir == Dir.DOWN:
            lowBoundRow = r

        else: # UP
            highBoundRow = r

        return lowBoundRow, highBoundRow, lowBoundCol, highBoundCol