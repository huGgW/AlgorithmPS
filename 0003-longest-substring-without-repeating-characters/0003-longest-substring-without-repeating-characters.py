class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        startIdx, endIdx = 0, -1
        maxStartIdx, maxEndIdx = 0, -1
        charSet = set()

        while endIdx < len(s) - 1:
            modified = False
            while endIdx < len(s) - 1 and s[endIdx+1] not in charSet:
                modified = True
                endIdx += 1
                charSet.add(s[endIdx])

            if modified:
                if maxEndIdx - maxStartIdx < endIdx - startIdx:
                    maxStartIdx, maxEndIdx = startIdx, endIdx
            else:
                while s[endIdx+1] in charSet:
                    charSet.remove(s[startIdx])
                    startIdx += 1

        return maxEndIdx - maxStartIdx + 1