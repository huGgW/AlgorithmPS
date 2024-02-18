class Solution:
    def maxLength(self, arr: List[str]) -> int:
        # make contains set of arr
        sets = list(map(lambda s: set(list(s)), arr))
        i = 0
        while i < len(arr):
            if len(arr[i]) != len(sets[i]):
                arr.pop(i)
                sets.pop(i)
            else:
                i += 1

        return self.backtrack(0, sets, set())

    def backtrack(self, i: int, sets: List[Set[str]], temp: Set[str]) -> int:
        if i == len(sets):
            return len(temp)
        
        # except
        exceptRes = self.backtrack(i+1, sets, temp)
        
        # contain if possible
        unionSet = temp.union(sets[i])
        if len(unionSet) < len(sets[i]) + len(temp): # not unique
            containRes = 0
        else:
            containRes = self.backtrack(i+1, sets, unionSet)
        
        return max(exceptRes, containRes)