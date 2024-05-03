from collections import Counter

class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        counter = Counter(p)
        notZeros = set(counter.keys())

        begins: List[int] = []
        for beg in range(len(s)-len(p)+1):
            end = beg + len(p) - 1
            if beg == 0:
                for c in s[beg:end+1]:
                    self.updateCharState(c, 'add', counter, notZeros)
            else:
                self.updateCharState(s[beg-1], 'remove', counter, notZeros)
                self.updateCharState(s[end], 'add', counter, notZeros)

            if not notZeros:
                begins.append(beg)

        return begins


    def updateCharState(self, char: str, removeOrAdd: str, counter: Counter[str], notZeros: Set[str]):
        if char in counter:
            if removeOrAdd == 'remove':
                counter[char] += 1
            elif removeOrAdd == 'add':
                counter[char] -= 1
            else:
                raise Exception

            if counter[char] == 0:
                notZeros.remove(char)
            else:
                notZeros.add(char)
