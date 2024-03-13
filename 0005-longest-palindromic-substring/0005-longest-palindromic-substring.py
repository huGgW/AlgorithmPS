class Solution:
    def longestPalindrome(self, s: str) -> str:
        # start idx of palins
        oddPalins = [i for i in range(len(s))]
        evenPalins = [i for i in range(len(s) - 1) if s[i] == s[i + 1]]
        oddLen, oddUpdated = (1, True) # because len(s) >= 1
        evenLen, evenUpdated = (2, True) if len(evenPalins) > 0 else (0, False)

        nextOdd = True
        while oddUpdated or evenUpdated:
            if nextOdd and oddUpdated:
                nextPalins = self.nextPalindromes(s, oddLen, oddPalins)
                if len(nextPalins) > 0:
                    oddPalins = nextPalins
                    oddLen += 2
                else:
                    oddUpdated = False

            elif not nextOdd and evenUpdated:
                nextPalins = self.nextPalindromes(s, evenLen, evenPalins)
                if len(nextPalins) > 0:
                    evenPalins = nextPalins
                    evenLen += 2
                else:
                    evenUpdated = False

            nextOdd = not nextOdd

        return s[oddPalins[0]:oddPalins[0]+oddLen] \
            if oddLen > evenLen \
            else s[evenPalins[0]:evenPalins[0]+evenLen]

    def nextPalindromes(
        self, s: str, ln: int, palins: List[int]
    ) -> List[int]:
        newPalins = [
            i - 1
            for i in palins
            if (i >= 1 and i + ln < len(s)) and s[i - 1] == s[i + ln]
        ]
        return newPalins

