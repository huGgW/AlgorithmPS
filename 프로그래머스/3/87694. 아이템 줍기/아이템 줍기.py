from typing import *
from enum import Enum

class Dir(Enum):
    UP = 0
    RIGHT = 1
    DOWN = 2
    LEFT = 3

class Corner:
    def __init__(self, x: int, y: int):
        self.pos = (x, y)

    def get_x(self) -> int:
        return self.pos[0]

    def get_y(self) -> int:
        return self.pos[1]

    @staticmethod
    def of(rectangle: List[int]) -> List["Corner"]:
        return [
            Corner(rectangle[0], rectangle[1]),
            Corner(rectangle[2], rectangle[1]),
            Corner(rectangle[0], rectangle[3]),
            Corner(rectangle[2], rectangle[3]),
        ]

class Intersection:
    def __init__(self, x: int, y: int):
        self.pos = (x, y)

    def get_x(self) -> int:
        return self.pos[0]

    def get_y(self) -> int:
        return self.pos[1]

    @staticmethod
    def of(rec1: List[int], rec2: List[int]) -> List["Intersection"]:
        # FIXME: Only include valid intersections
        one_sx, one_bx = rec1[0], rec1[2]
        one_sy, one_by = rec1[1], rec1[3]
        two_sx, two_bx = rec2[0], rec2[2]
        two_sy, two_by = rec2[1], rec2[3]

        intersections: List["Intersection"] = []
        
        # Check one rec vert cross points
        for y in range(one_sy, one_by + 1):
            if (two_sx < one_sx < two_bx) and (y == two_sy or y == two_by):
                intersections.append(Intersection(one_sx, y))
            if (two_sx < one_bx < two_bx) and (y == two_sy or y == two_by):
                intersections.append(Intersection(one_bx, y))

        # Check one rec horiz cross points
        for x in range(one_sx, one_bx + 1):
            if (two_sy < one_sy < two_by) and (x == two_sx or x == two_bx):
                intersections.append(Intersection(x, one_sy))
            if (two_sy < one_by < two_by) and (x == two_sx or x == two_bx):
                intersections.append(Intersection(x, one_by))

        return intersections

def solution(
    rectangle: List[List[int]],
    characterX: int,
    characterY: int,
    itemX: int,
    itemY: int,
):
    points: List[Union[Corner, Intersection]] = []

    # Add corners
    for rec in rectangle:
        points += Corner.of(rec)

    # Add intersections
    for i in range(len(rectangle)):
        for j in range(i+1, len(rectangle)):
            points += Intersection.of(rectangle[i], rectangle[j])

    # sort points
    xpoints = sorted(points, key=lambda p: (p.get_x(), p.get_y()))
    ypoints = sorted(points, key=lambda p: (p.get_y(), p.get_x()))
    del points

    # find downmost leftmost point
    initX, initY = 0, 0
    for point in ypoints:
        if isinstance(point, Corner):
            initX, initY = point.get_x(), point.get_y()
            break

    # move while record distance
    distOne = 0
    distTwo = 0
    isOne = True
    def addDist(d: int):
        nonlocal distOne, distTwo, isOne
        if isOne:
            distOne += d
        else:
            distTwo += d

    def toggleDist():
        nonlocal isOne
        isOne = not isOne

    dir = Dir.UP
    currX, currY = initX, initY
    while True:
        # get next point to move
        if dir == Dir.UP or dir == Dir.DOWN:
            nextP = findNextVertPoint(xpoints, currX, currY, dir)
        else:
            nextP = findNextHorizPoint(ypoints, currX, currY, dir)
        nextX, nextY = nextP.get_x(), nextP.get_y()

        # check if character is in the way
        isCharacterInWay = (
            (dir == Dir.UP or dir == Dir.DOWN)
            and currX == characterX
            and (
                (currY - characterY) * (nextY - characterY) < 0
                or nextY == characterY
            )
        ) or (
            (dir == Dir.LEFT or dir == Dir.RIGHT)
            and currY == characterY
            and (
                (currX - characterX) * (nextX - characterX) < 0
                or nextX == characterX
            )
        )

        # check if item is in the way
        isItemInWay = (
            (dir == Dir.UP or dir == Dir.DOWN) \
            and currX == itemX \
            and (
                (currY - itemY) * (nextY - itemY) < 0
                or nextY == itemY
            )
        ) or (
            (dir == Dir.LEFT or dir == Dir.RIGHT) \
            and currY == itemY \
            and (
                (currX - itemX) * (nextX - itemX) < 0
                or nextX == itemX
            )
        )

        # add distance accordingly
        if not isCharacterInWay and not isItemInWay:
            addDist(abs(currX - nextX) + abs(currY - nextY))
        elif isCharacterInWay and not isItemInWay:
            addDist(abs(currX - characterX) + abs(currY - characterY))
            toggleDist()
            addDist(abs(nextX - characterX) + abs(nextY - characterY))
        elif not isCharacterInWay and isItemInWay:
            addDist(abs(currX - itemX) + abs(currY - itemY))
            toggleDist()
            addDist(abs(nextX - itemX) + abs(nextY - itemY))
        else: # both in the way
            smallPin = min((characterX, characterY), (itemX, itemY))
            bigPin = max((characterX, characterY), (itemX, itemY))

            if dir == Dir.UP or dir == Dir.RIGHT:
                firstPin, secondPin = smallPin, bigPin
            else:
                firstPin, secondPin = bigPin, smallPin

            addDist(abs(currX - firstPin[0]) + abs(currY - firstPin[1]))
            toggleDist()
            addDist(abs(firstPin[0] - secondPin[0]) + abs(firstPin[1] - secondPin[1]))
            toggleDist()
            addDist(abs(secondPin[0] - nextX) + abs(secondPin[1] - nextY))

        # move to next point
        currX, currY = nextX, nextY
        dir = Dir((dir.value + 1) % 4) if isinstance(nextP, Corner) else Dir((dir.value + 3) % 4)

        # check if reached the initial point
        if currX == initX and currY == initY:
            break

    answer = min(distOne, distTwo)
    return answer

def findNextVertPoint(
    xpoints: List[Union[Corner, Intersection]],
    currX: int,
    currY: int,
    dir: Dir
) -> Union[Corner, Intersection]:
    key = lambda p: (p.get_x(), p.get_y())
    idx = findPointIdx(xpoints, (currX, currY), key)

    if dir == Dir.UP:
        return xpoints[idx+1]
    elif dir == Dir.DOWN:
        return xpoints[idx-1]
    else:
        raise Exception("Invalid direction")

def findNextHorizPoint(
    ypoints: List[Union[Corner, Intersection]],
    currX: int,
    currY: int,
    dir: Dir
) -> Union[Corner, Intersection]:
    key = lambda p: (p.get_y(), p.get_x())
    idx = findPointIdx(ypoints, (currY, currX), key)

    if dir == Dir.RIGHT:
        return ypoints[idx+1]
    elif dir == Dir.LEFT:
        return ypoints[idx-1]
    else:
        raise Exception("Invalid direction")

def findPointIdx(
    points: List[Union[Corner, Intersection]],
    findKey: Tuple[int, int],
    key: Callable,
) -> int:
    b, e = 0, len(points)-1

    while b <= e:
        m = (b + e) // 2
        midP = points[m]
        midK = key(midP)

        if midK == findKey:
            return m
        elif findKey < midK:
            e = m - 1
        else:
            b = m + 1

    raise Exception("Not found")
