from typing import *
import sys
input = sys.stdin.readline

def main(n: int, m: int, games: List[Tuple[int, int]]) -> int:
    connected: Dict[int, Set[int]] = {}

    for i, game in enumerate(games):
        g = i + 1

        fstOptSet = connected.get(game[0])
        scdOptSet = connected.get(game[1])

        if fstOptSet is not None and scdOptSet is not None:
            if fstOptSet == scdOptSet: # loop created
                return g
            else: # two acyclic graph merged
                for k in scdOptSet:
                    connected[k] = fstOptSet
                fstOptSet.update(scdOptSet)
        elif fstOptSet is not None:
            fstOptSet.add(game[1])
            connected[game[1]] = fstOptSet
        elif scdOptSet is not None:
            scdOptSet.add(game[0])
            connected[game[0]] = scdOptSet
        else:
            newSet = { game[0], game[1] }
            connected[game[0]] = newSet
            connected[game[1]] = newSet

    return 0


if __name__ == "__main__":
    n, m = map(int, input().split()) 
    games: List[Tuple] = [
        tuple(map(int, input().split()))
        for _ in range(m)
    ]

    result = main(n, m, games)
    print(result)
