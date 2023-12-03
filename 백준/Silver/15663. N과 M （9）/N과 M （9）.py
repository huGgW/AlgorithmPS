n, m = map(int, input().split())

nums = list(map(int, input().split()))

results = set()

def addResult(mth: int = 0, idxs: list = []):
    if mth == m:
        results.add(
            tuple(map(lambda i: nums[i], idxs))
        )
        return

    for idx in range(n):
        if idx in idxs:
            continue

        idxs.append(idx)

        addResult(mth+1, idxs)

        idxs.pop(-1)

addResult()

results = sorted(results)

str = ""
for result in results:
    for x in result:
        str += f"{x} "
    str = str[:-1] + "\n"

print(str[:-1])
