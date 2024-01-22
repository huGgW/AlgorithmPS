from typing import *
import sys
sys.setrecursionlimit(10**6)

import math

def solution(fees: List[int], records: List[str]) -> List[int]:
    basicTime, basicFee, interTime, interFee = fees
    enterHist: Dict[str, int] = {}
    totalTimes: Dict[str, int] = {}

    for rec in records:
        time, car, command = rec.split(" ")
        min = timeStrToMin(time)

        if command == "IN":
            enterHist[car] = min
        elif command == "OUT":
            enterMin = enterHist[car]
            totalTimes[car] = totalTimes.get(car, 0) + (min - enterMin)
            del enterHist[car]
            
    lastMin = 23 * 60 + 59
    for leftCar, enterMin in enterHist.items():
        totalTimes[leftCar] = totalTimes.get(leftCar, 0) + (lastMin - enterMin)
    del enterHist
            
    return list(
        map(
            lambda tup : calcFee(
                tup[1], basicFee, basicTime, interFee, interTime
            ),
            sorted(totalTimes.items())
        )
    )



def timeStrToMin(timeStr: str) -> int:
    h, m = map(int, timeStr.split(":"))
    return h*60 + m


def calcFee(
    mins: int, basicFee: int,
    basicTime: int, interFee: int,
    interTime: int
) -> int:
    if mins <= basicTime:
        return basicFee

    extraMin = mins - basicTime
    multFee = math.ceil(extraMin / interTime)
    return basicFee + multFee * interFee