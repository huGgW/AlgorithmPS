from collections import deque
import heapq

def solution(priorities, location):
    hp = [-p for p in priorities]
    heapq.heapify(hp)
    
    ptr = 0
    cnt = 0
    while hp:
        nextPriority = -1 * heapq.heappop(hp)
        while priorities[ptr] != nextPriority:
            ptr = (ptr + 1) % len(priorities)
        priorities[ptr] = 0
        cnt += 1
        
        if ptr == location:
            break
    
    return cnt
