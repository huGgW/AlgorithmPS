def solution(A,B):
    return sum(
        map(
            lambda t: t[0] * t[1],
            zip(sorted(A), sorted(B, key=lambda x: -x))
        )
    )
