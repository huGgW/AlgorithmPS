from typing import *
import heapq

def solution(jobs):
    tasks = [tuple(ls) for ls in jobs] # (start, duration)
    waiting = [] # (duration, start)
    
    heapq.heapify(tasks)
    time = 0
    waitingTime = 0
    
    while tasks or waiting:
        if tasks:
            if not waiting and time < tasks[0][0]:
                time = tasks[0][0]
            while tasks and tasks[0][0] <= time:
                start, duration = heapq.heappop(tasks)
                heapq.heappush(waiting, (duration, start))
                
        if waiting:
            duration, start = heapq.heappop(waiting)
            endTime = time + duration
            waitingTime += endTime - start
            time = endTime
            
    return waitingTime // len(jobs)
