class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        ranges: Dict[str, Tuple[int, int]] = {}
        for i, s in enumerate(s):
            if s not in ranges:
                ranges[s] = (i, i)
            else:
                ranges[s] = (ranges[s][0], i)

        rangeLs = list(ranges.values())
        groups = []
        for b, e in rangeLs:
            excludeIdx = None
            bb, ee = None, None
            for (i, (gb, ge)) in enumerate(groups):
                if not (gb > e or ge < b):
                    bb = min(gb, b)
                    ee = max(ge, e)
                    excludeIdx = i
                    break

            if excludeIdx is not None:
                del groups[excludeIdx]
                groups.append((bb, ee))
            else:
                groups.append((b, e))

        return [
            t[1] - t[0] + 1 \
            for t in sorted(groups)
        ]