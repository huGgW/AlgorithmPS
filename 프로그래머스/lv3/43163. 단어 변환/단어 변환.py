def solution(begin: str, target: str, words: list):
    toVisit = set(words)

    if target not in toVisit:
        return 0

    answer = 0
    currentSet = set()
    currentSet.add(begin)
    while toVisit:
        nextSet = set()
        for current in currentSet:
            for next in toVisit:
                if isChangable(current, next):
                    if next == target:
                        return answer + 1
                    nextSet.add(next)
            toVisit -= nextSet
        answer += 1
        currentSet = nextSet

    return 0

def isChangable(a: str, b: str) -> bool:
    diff = 0
    for i in range(len(a)):
        if a[i] != b[i]:
            diff += 1
        if diff > 1:
            return False
    return True
