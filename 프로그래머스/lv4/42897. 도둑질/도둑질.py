def solution(money: list) -> int:
    table = dict()

    initExclude = (1, len(money) - 1)
    initInclude = (2, len(money) - 2)

    # for init exclude
    for i in range(len(money) - 1, 0, -1):
        j = len(money) - 1
        if i == j:
            table[(i, j)] = money[i]
        elif i + 1 == j:
            table[(i, j)] = max(money[i], money[i+1])
        else:
            table[(i, j)] = max(
                table[(i+1, j)],
                money[i] + table[(i + 2, j)]
            )

    # for init include
    for i in range(len(money) - 2, 1, -1):
        j = len(money) - 2
        if i == j:
            table[(i, j)] = money[i]
        elif i + 1 == j:
            table[(i, j)] = max(money[i], money[i+1])
        else:
            table[(i, j)] = max(
                table[(i+1, j)],
                money[i] + table[(i + 2, j)]
            )

    return max(
        table[(initExclude)],
        money[0] + table[(initInclude)]
    )
