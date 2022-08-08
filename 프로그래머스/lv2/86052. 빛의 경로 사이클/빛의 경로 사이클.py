from enum import Enum, auto
from typing import *

class Dir(Enum):
    UP = auto()
    DOWN = auto()
    LEFT = auto()
    RIGHT = auto()

    def reverse(self):
        if self == Dir.UP:
            return Dir.DOWN
        elif self == Dir.DOWN:
            return Dir.UP
        elif self == Dir.LEFT:
            return Dir.RIGHT
        else: # self == Dir.RIGHT
            return Dir.LEFT


class Path(Enum):
    S = auto()
    R = auto()
    L = auto()

    @staticmethod
    def fromStr(s: str):
        if s == "S":
            return Path.S
        elif s == "R":
            return Path.R
        elif s == "L": # s == L
            return Path.L
    
    def nextDir(self, inDir):
        if self == Path.S:
            return inDir
        elif self == Path.R:
            if inDir == Dir.UP:
                return Dir.RIGHT
            elif inDir == Dir.RIGHT:
                return Dir.DOWN
            elif inDir == Dir.DOWN:
                return Dir.LEFT
            else: # dir == Dir.LEFT
                return Dir.UP
        else: # path == Path.L:
            if inDir == Dir.UP:
                return Dir.LEFT
            elif inDir == Dir.LEFT:
                return Dir.DOWN
            elif inDir == Dir.DOWN:
                return Dir.RIGHT
            else: # dir == Dir.RIGHT
                return Dir.UP



class Node:
    def __init__(self, t: Path):
        self.path: Path = t
        self.next: Dict[Dir, Node] = {
            Dir.UP: None,
            Dir.DOWN: None,
            Dir.LEFT: None,
            Dir.RIGHT: None,
        }
        self.inDirChecked: Dict[Dir, bool] = {
            Dir.UP: False,
            Dir.DOWN: False,
            Dir.LEFT: False,
            Dir.RIGHT: False,
        }
        # self.visited = False

    def setNextNode(self, dir: Dir, node):
        self.next[dir] = node
    
    def checkInDir(self, inDir: Dir):
        dir: Dir = inDir.reverse()
        self.inDirChecked[dir] = True
        
    def passedInDir(self, inDir: Dir) -> bool:
        dir: Dir = inDir.reverse()
        return self.inDirChecked[dir]

    # inDir: perspective of before node
    def moveNextNode(self, inDir: Dir) -> 'Node':
        nextDir = self.path.nextDir(inDir)
        nextNode: Node = self.next[nextDir]
        nextNode.checkInDir(nextDir)
        return nextNode


def createNodes(w: int, h: int, grid: List[str]) -> List[List[Node]]:
    nodes: List[List[Node]] = [[None for _ in range(w)] for _ in range(h)]
    for y in range(h):
        paths: str = grid[y]
        for x in range(w):
            nodes[y][x] = Node(Path.fromStr(paths[x]))
    
    return nodes

def setNodesRelation(w: int, h: int, nodes: List[List[Node]]):
    for y in range(h):
        for x in range(w):
            node: Node = nodes[y][x]
            node.setNextNode(
                dir=Dir.UP,
                node=nodes[y-1 if y > 0 else h-1][x]
            )
            node.setNextNode(
                dir=Dir.DOWN,
                node=nodes[y+1 if y < h-1 else 0][x]
            )
            node.setNextNode(
                dir=Dir.LEFT,
                node=nodes[y][x-1 if x > 0 else w-1]
            )
            node.setNextNode(
                dir=Dir.RIGHT,
                node=nodes[y][x+1 if x < w-1 else 0]
            )

def cntCycle(node: Node, inDir: Dir) -> int: # assume always cycle exists
    initial: Tuple[Node, Dir] = (node, inDir)
    node.checkInDir(inDir)
    cnt: int = 1
    while True:
        node, inDir = node.moveNextNode(inDir), node.path.nextDir(inDir)
        tmp: Tuple[Node, Dir] = (node, inDir)
        if tmp == initial:
            return cnt
        else:
            cnt += 1

def solution(grid) -> List[int]:
    w: int = len(grid[0])
    h: int = len(grid)

    nodes: List[List[Node]] = createNodes(w, h, grid)
    setNodesRelation(w, h, nodes)

    answer: List[int] = []
    for nodel in nodes: 
        for node in nodel:
            for inDir in [Dir.LEFT, Dir.UP, Dir.RIGHT, Dir.DOWN]:
                if not node.passedInDir(inDir):
                    cycleLen = cntCycle(node, inDir)
                    answer.append(cycleLen)
                    
    answer.sort()
    return answer