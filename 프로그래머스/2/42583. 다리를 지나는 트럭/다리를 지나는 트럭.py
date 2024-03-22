from typing import *
from collections import deque
import math

def solution(bridge_length, weight, truck_weights):
    bridge = deque()
    totalWeight = 0
    time = 1
    idx = 0
    
    while idx < len(truck_weights) or bridge:
        if bridge and bridge[0][2] <= time:
            ow, oit, oot = bridge.popleft()
            totalWeight -= ow
            continue
            
        if len(bridge) < bridge_length \
        and idx <  len(truck_weights) \
        and truck_weights[idx] + totalWeight <= weight:
            (inTime, outTime) = (time, time+bridge_length)
            addWeight = truck_weights[idx]
            bridge.append((addWeight, inTime, outTime))
            totalWeight += addWeight
            
            idx += 1
            time += 1
            continue
            
        time = bridge[0][2] if bridge else time
    
    return time