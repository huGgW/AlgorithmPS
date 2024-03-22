from bisect import *

def solution(citations):
    citations.sort()
    
    b = citations[0]
    e = min(citations[-1], len(citations))
    while b <= e:
        m = (b + e) // 2
        bisectIdx = bisect_left(citations, m)
        cnt = len(citations) - bisectIdx
        
        if cnt >= m:
            b = m + 1
        else:
            e = m - 1
            
    return e
