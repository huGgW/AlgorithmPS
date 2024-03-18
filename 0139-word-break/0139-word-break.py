from typing import *
from dataclasses import dataclass

@dataclass(frozen=True)
class State:
    begin: int 
    current: int

class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        stack: List[State] = [State(0, 0)]
        wordSet = set(wordDict)
        failBeginSet = set()

        while stack:
            state = stack.pop(-1)

            if state.begin in failBeginSet:
                continue

            nextPart = s[state.begin : state.current+1]
            isBreakable = nextPart in wordSet
            isFinal = state.current == len(s)-1

            if isFinal and isBreakable:
                return True
            elif isFinal and not isBreakable:
                failBeginSet.add(state.begin)
            elif not isFinal and isBreakable:
                stack += [
                    State(state.begin, state.current + 1),
                    State(state.current + 1, state.current + 1),
                ]
            else:
                stack.append(State(state.begin, state.current + 1))

        return False
