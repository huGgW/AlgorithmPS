from typing import *
from dataclasses import dataclass
import sys

input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush

@dataclass
class Node:
    key: str
    left: Optional["Node"]
    right: Optional["Node"]

nodes: Dict[str, Node] = {"A": Node("A", None, None)}

# init tree
n = int(input())

for _ in range(n):
    n, l, r = input().split()

    node: Node
    if n not in nodes:
        node = Node(n, None, None)
    node = nodes[n]

    if l != '.':
        if l not in nodes:
            nodes[l] = Node(l, None, None)
        node.left = nodes[l]

    if r != '.':
        if r not in nodes:
            nodes[r] = Node(r, None, None)
        node.right = nodes[r]

# preorder
stack: List[Tuple[Node, str]] = [(nodes["A"], "v")]
out: str = ""
while stack:
    node, command = stack.pop()

    if command == "v":
        if node.right:
            stack.append((node.right, "v"))
        if node.left:
            stack.append((node.left, "v"))
        stack.append((node, "p"))
        
    elif command == "p":
        out += node.key

output(out + "\n")

# inorder
stack: List[Tuple[Node, str]] = [(nodes["A"], "v")]
out: str = ""
while stack:
    node, command = stack.pop()

    if command == "v":
        if node.right:
            stack.append((node.right, "v"))
        stack.append((node, "p"))
        if node.left:
            stack.append((node.left, "v"))
        
    elif command == "p":
        out += node.key

output(out + "\n")

# postorder
stack: List[Tuple[Node, str]] = [(nodes["A"], "v")]
out: str = ""
while stack:
    node, command = stack.pop()

    if command == "v":
        stack.append((node, "p"))
        if node.right:
            stack.append((node.right, "v"))
        if node.left:
            stack.append((node.left, "v"))
        
    elif command == "p":
        out += node.key

output(out + "\n")

flush()
