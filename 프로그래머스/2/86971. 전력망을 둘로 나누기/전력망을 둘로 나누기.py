from typing import *
from collections import deque

def solution(n, wires):
    # build graph
    graph: Dict[int, Set[int]] = {v:set() for v in range(1, n+1)}
    for v1, v2 in wires:
        graph[v1].add(v2)
        graph[v2].add(v1)
        
    # cut wire and analyze
    minAbs = n
    for cv1, cv2 in wires:
        graph[cv1].remove(cv2)
        graph[cv2].remove(cv1)
        
        visited = set()
        toVisit = deque([cv1])
        while toVisit:
            v = toVisit.popleft()
            visited.add(v)
            neighbors = graph[v]
            for neighbor in neighbors.difference(visited):
            	toVisit.append(neighbor)
                
        absDiff = abs(len(visited) - (n - len(visited)))
        minAbs = min(absDiff, minAbs)
        
        graph[cv1].add(cv2)
        graph[cv2].add(cv1)
        
        
    return minAbs
        

            
