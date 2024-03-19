from typing import *
from enum import Enum, auto
from dataclasses import dataclass
from collections import deque

import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

class Dir(Enum):
    LEFT = auto()
    RIGHT = auto()
    UP = auto()
    DOWN = auto()

INHOLE = (-1, -1)

@dataclass(frozen = True)
class State:
    red: Tuple[int, int]
    blue: Tuple[int, int]

def move(
    board: List[List[str]], state: State, dir: Dir
) -> State:
    red, blue = state.red, state.blue

    if dir == Dir.UP:
        if red[1] == blue[1]:
            isRedFirst = red[0] < blue[0]
            first, second = (red, blue) if isRedFirst else (blue, red)

            for i in range(first[0]-1, -1, -1):
                first = (i + 1, first[1])

                if board[i][first[1]] == '#':
                    break

                elif board[i][first[1]] == 'O':
                    first = INHOLE
                    break

            for i in range(second[0]-1, -1 if first[0] == -1 else first[0]-1, -1):
                second = (i + 1, second[1])

                if board[i][second[1]] == '#':
                    break

                elif board[i][second[1]] == 'O':
                    second = INHOLE
                    break

            red, blue = (first, second) if isRedFirst else (second, first)

        else:
            for i in range(red[0]-1, -1, -1):
                red = (i + 1, red[1])

                if board[i][red[1]] == '#':
                    break

                elif board[i][red[1]] == 'O':
                    red = INHOLE
                    break

            for i in range(blue[0]-1, -1, -1):
                blue = (i + 1, blue[1])

                if board[i][blue[1]] == '#':
                    break

                elif board[i][blue[1]] == 'O':
                    blue = INHOLE
                    break


    elif dir == Dir.DOWN:
        if red[1] == blue[1]:
            isRedFirst = red[0] > blue[0]
            first, second = (red, blue) if isRedFirst else (blue, red)

            for i in range(first[0]+1, len(board)):
                first = (i - 1, first[1])

                if board[i][first[1]] == '#':
                    break

                elif board[i][first[1]] == 'O':
                    first = INHOLE
                    break

            for i in range(second[0]+1, len(board) if first[0] == -1 else first[0]+1, 1):
                second = (i - 1, second[1])

                if board[i][second[1]] == '#':
                    break

                elif board[i][second[1]] == 'O':
                    second = INHOLE
                    break

            red, blue = (first, second) if isRedFirst else (second, first)

        else:
            for i in range(red[0]+1, len(board)):
                red = (i - 1, red[1])

                if board[i][red[1]] == '#':
                    break

                elif board[i][red[1]] == 'O':
                    red = INHOLE
                    break

            for i in range(blue[0]+1, len(board)):
                blue = (i - 1, blue[1])

                if board[i][blue[1]] == '#':
                    break

                elif board[i][blue[1]] == 'O':
                    blue = INHOLE
                    break

    elif dir == Dir.LEFT:
        if red[0] == blue[0]:
            isRedFirst = red[1] < blue[1]
            first, second = (red, blue) if isRedFirst else (blue, red)

            for j in range(first[1]-1, -1, -1):
                first = (first[0], j + 1)

                if board[first[0]][j] == '#':
                    break

                elif board[first[0]][j] == 'O':
                    first = INHOLE
                    break

            for j in range(second[1]-1, -1 if first[1] == -1 else first[1]-1, -1):
                second = (second[0], j + 1)

                if board[second[0]][j] == '#':
                    break

                elif board[second[0]][j] == 'O':
                    second = INHOLE
                    break

            red, blue = (first, second) if isRedFirst else (second, first)

        else:
            for j in range(red[1]-1, -1, -1):
                red = (red[0], j + 1)

                if board[red[0]][j] == '#':
                    break

                elif board[red[0]][j] == 'O':
                    red = INHOLE
                    break

            for j in range(blue[1]-1, -1, -1):
                blue = (blue[0], j + 1)

                if board[blue[0]][j] == '#':
                    break

                elif board[blue[0]][j] == 'O':
                    blue = INHOLE
                    break

    elif dir == Dir.RIGHT:
        if red[0] == blue[0]:
            isRedFirst = red[1] > blue[1]
            first, second = (red, blue) if isRedFirst else (blue, red)

            for j in range(first[1]+1, len(board[0])):
                first = (first[0], j - 1)

                if board[first[0]][j] == '#':
                    break

                elif board[first[0]][j] == 'O':
                    first = INHOLE
                    break

            for j in range(second[1]+1, len(board[0]) if first[1] == -1 else first[1]+1):
                second = (second[0], j - 1)

                if board[second[0]][j] == '#':
                    break

                elif board[second[0]][j] == 'O':
                    second = INHOLE
                    break

            red, blue = (first, second) if isRedFirst else (second, first)

        else:
            for j in range(red[1]+1, len(board[0])):
                red = (red[0], j - 1)

                if board[red[0]][j] == '#':
                    break

                elif board[red[0]][j] == 'O':
                    red = INHOLE
                    break

            for j in range(blue[1]+1, len(board[0])):
                blue = (blue[0], j - 1)

                if board[blue[0]][j] == '#':
                    break

                elif board[blue[0]][j] == 'O':
                    blue = INHOLE
                    break

    return State(red, blue)


def main(board: List[List[str]], red: Tuple[int, int], blue: Tuple[int, int]) -> int:
    queue: deque[Tuple[State, int]] = deque()
    queue.append((State(red, blue), 0))
    history: Set[State] = { State(red, blue) }

    while len(queue) > 0:
        state, cnt = queue.popleft()
        if cnt >= 10:
            continue

        for movedStates in map(lambda dir: move(board, state, dir), Dir):
            if movedStates.blue == INHOLE or movedStates in history:
                continue

            if movedStates.red == INHOLE:
                return cnt + 1

            history.add(movedStates)
            queue.append((movedStates, cnt + 1))

    return -1


if __name__ == "__main__":
    rows, cols = map(int, input().split())
    board = [
        list(input()) for _ in range(rows)
    ]

    red, blue, hole = (-1, -1), (-1, -1), (-1, -1)
    for i in range(rows):
        for j in range(cols):
            if board[i][j] == 'R':
                red = (i, j)
                board[i][j] = '.'
            elif board[i][j] == 'B':
                blue = (i, j)
                board[i][j] = '.'
            # elif board[i][j] == 'O':
            #     hole = (i, j)

    result = main(board, red, blue)
    print(result)
