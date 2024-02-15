class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        self.board = board
        self.word = word

        # Edge Case: 1x1 block
        if len(self.board) == 1 and len(self.board[0]) == 1:
            return len(self.word) == 1 and self.board[0][0] == self.word[0]

        for i in range(len(self.board)):
            for j in range(len(self.board[0])):
                if self.wordExistsOn(i, j, 0):
                    return True
        return False

    def wordExistsOn(self, i: int, j: int, wIdx: int) -> bool:
        if wIdx == len(self.word):
            return True

        if self.board[i][j] != self.word[wIdx]:
            return False

        result = False

        self.board[i][j] = '!'
        for (ni, nj) in self.neighbors(i, j):
            result = result or self.wordExistsOn(ni, nj, wIdx+1)
        self.board[i][j] = self.word[wIdx]

        return result
            


    def neighbors(self, i: int, j: int) -> Generator[Tuple[int, int], None, None]:
        for ni, nj in [(i-1, j), (i, j-1), (i+1, j), (i, j+1)]:
            if (0 <= ni < len(self.board)) and (0 <= nj < len(self.board[0])):
                yield (ni, nj)

