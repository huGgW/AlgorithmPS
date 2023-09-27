from typing import *

def solution(maps: List[List[int]]):
    n = len(maps)
    m = len(maps[0])
    
    # Wrap map with 0 border
    maps = [[0 for i in range(m)]] + maps + [[0 for i in range(m)]]
    for i in range(len(maps)):
        maps[i] = [0] + maps[i] + [0]

    # BFS
    queue = [(0, (1, 1))]
    while queue:
        # Move
        curr = queue.pop(0)
        cnt = curr[0]
        pos = curr[1]
        if maps[pos[0]][pos[1]] == 0:
            continue
        maps[pos[0]][pos[1]] = 0
        cnt += 1
        
        # When arrived to destination
        if pos == (n, m):
            return cnt
            
        # Branch out
        l = (pos[0], pos[1]-1)
        r = (pos[0], pos[1]+1)
        u = (pos[0]-1, pos[1])
        d = (pos[0]+1, pos[1])
        
        for p in [d, r, u, l]:
            if p == (n, m):
                return cnt + 1
            elif maps[p[0]][p[1]] == 1:
                queue.append((cnt, p))
    
    return -1
