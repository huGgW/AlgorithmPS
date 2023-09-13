from typing import List, Tuple

def solution(number: str, k: int) -> str:
    pos = 0
    for i in range(k):
        pos, number = remove_rise(number, pos)

    return number

def remove_rise(number: str, since: int) -> Tuple[int, str]:
    pos: int = len(number)-1
    for i in range(since, len(number)-1):
        if int(number[i]) < int(number[i+1]):
            pos = i
            break

    if pos < len(number)-1:
        number = number[:pos] + number[pos+1:]
    else:
        number = number[:pos]

    if pos > 0:
        pos -= 1

    return (pos, number)
