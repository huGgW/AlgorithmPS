from copy import deepcopy
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

def main():
    # read sudoku
    sudoku: list[list[int]] = [[] for _ in range(9)]
    for i in range(9):
        sudoku[i] = list(map(int, input().strip()))

    # analyze sudoku
    rowNeeds, colNeeds, boxNeeds = analyzeSudoku(sudoku)

    # traverse
    traverse(0, 0, sudoku, rowNeeds, colNeeds, boxNeeds)

    printSudoku(sudoku)


def next(i: int, j: int, N: int) -> tuple[int, int] | None:
    j += 1
    if j == N:
        j = 0
        i += 1

    if i == N:
        return None
    else:
        return i, j

def analyzeSudoku(
        sudoku: list[list[int]]
) -> tuple[list[set[int]], list[set[int]], list[list[set[int]]]]:
    # analyze sudoku
    rowNeeds: list[set[int]] = [set() for _ in range(9)]
    colNeeds = deepcopy(rowNeeds)
    boxNeeds: list[list[set[int]]] = [[set() for _ in range(3)] for _ in range(3)]
    full = set(range(1, 10))

    for i in range(9):
        rowHas = set(sudoku[i])
        colHas = set(map(lambda j: sudoku[j][i], range(9)))
        rowNeeds[i] = full.difference(rowHas)
        colNeeds[i] = full.difference(colHas)

    for i in range(3):
        for j in range(3):
            boxHas = set()
            for ii in range(3):
                for jj in range(3):
                    boxHas.add(sudoku[i*3 + ii][j*3 + jj])
            boxNeeds[i][j] = full.difference(boxHas)

    return rowNeeds, colNeeds, boxNeeds


def traverse(
        i: int, j: int, 
        sudoku: list[list[int]],
        rowNeeds: list[set[int]],
        colNeeds: list[set[int]],
        boxNeeds: list[list[set[int]]],
) -> bool:
    # goto next fill position
    while sudoku[i][j] > 0:
        nextPos = next(i, j, 9)
        if nextPos is None:
            return True
        i, j = nextPos

    # check possible numbers
    for x in sorted(
        rowNeeds[i].intersection(colNeeds[j]).intersection(boxNeeds[i//3][j//3])
    ):
        sudoku[i][j] = x
        rowNeeds[i].remove(x)
        colNeeds[j].remove(x)
        boxNeeds[i//3][j//3].remove(x)

        result = traverse(i, j, sudoku, rowNeeds, colNeeds, boxNeeds)
        if result:
            return True

        rowNeeds[i].add(x)
        colNeeds[j].add(x)
        boxNeeds[i//3][j//3].add(x)
        sudoku[i][j] = 0
    
    return False

def printSudoku(sudoku: list[list[int]]):
    for ls in sudoku:
        for x in ls:
            output(str(x))
        output("\n")

if __name__ == "__main__":
    main()