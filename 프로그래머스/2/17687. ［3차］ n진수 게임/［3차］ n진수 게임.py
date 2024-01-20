from typing import *
import sys
sys.setrecursionlimit(10**6)

numToStr: List[str] = [
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'A', 'B', 'C', 'D', 'E', 'F'
]

def solution(n: int, t: int, m: int, p: int) -> str:
    answer = ""
    
    transNum: List[int] = [0]
    pointer = p - 1
    
    while t > 0:
        while len(transNum) <= pointer:
            pointer -= len(transNum)
            addOne(transNum, n)
        answer += numToStr[transNum[pointer]]
        pointer += m
        t -= 1
        
    return answer
            
def addOne(transNum: List[int], base: int):
    idx = len(transNum)-1
    transNum[idx] += 1
    
    while transNum[idx] >= base:
        transNum[idx] -= base
        if idx > 0:
            transNum[idx-1] += 1
            idx -= 1
        else:
            transNum.insert(0, 1)
            break