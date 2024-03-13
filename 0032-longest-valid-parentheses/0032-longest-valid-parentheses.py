class Solution:
    def longestValidParentheses(self, s: str) -> int:
        self.s = s
        validParens: List[Tuple[int, int]] = []

        # add basic parens
        for b in range(0, len(s) - 1):
            e = b + 1
            if self.isParen(b, e):
                validParens.append((b, e))

        changed = True
        while changed:
            changed = False

            # merge continuous parens
            if len(validParens) >= 2:
                mergedValidParens = [validParens[0]]
                for scdParenIdx in range(1, len(validParens)):
                    fstParen = mergedValidParens.pop(-1)
                    scdParen = validParens[scdParenIdx]

                    if fstParen[1] + 1 == scdParen[0]:
                        changed = True
                        mergedParen = (fstParen[0], scdParen[1])
                        mergedValidParens.append(mergedParen)
                    else:
                        mergedValidParens += [fstParen, scdParen]

                validParens = mergedValidParens

            # wrap available parens
            def wrapIsPossibleAndChangeState(tup: Tuple[int, int]) -> Tuple[int, int]:
                nonlocal changed
                b, e = tup

                while self.isWrappable(b, e):
                    changed = True
                    b -= 1; e += 1

                return (b, e)
                

            validParens = [wrapIsPossibleAndChangeState(tup) for tup in validParens]

        return max(
            map(
                lambda tup: tup[1] - tup[0] + 1,
                validParens
            )
        ) if validParens else 0

    def isParen(self, b: int, e: int) -> bool:
        return self.s[b] == "(" and self.s[e] == ")"

    def isWrappable(self, b: int, e: int) -> bool:
        if b == 0 or e == len(self.s)-1:
            return False

        return self.isParen(b-1, e+1)
