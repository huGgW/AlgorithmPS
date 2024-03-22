import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush
sys.setrecursionlimit(10**6)

class UnionFind:
    def __init__(self, n):
        self.parent = [-1 for i in range(n+1)]

    def find(self, x) -> int:
        p = self.parent[x]

        if p < 0: # it is root
            return x

        root = self.find(p)
        self.parent[x] = root # compress
        return root

    def union(self, x, y):
        rootX = self.find(x)
        rootY = self.find(y)
        if rootX == rootY:
            return

        rankX = -self.parent[rootX]
        rankY = -self.parent[rootY]

        if rankX < rankY:
            self.parent[rootX] = rootY
        elif rankX > rankY:
            self.parent[rootY] = rootX
        else:
            self.parent[rootX] -= 1
            self.parent[rootY] = rootX


if __name__ == "__main__":
    n, m = map(int, input().split())
    sets = UnionFind(n)

    for _ in range(m):
        cmd, x, y = map(int, input().split())
        if cmd == 0: # union
            sets.union(x, y)
        else: # find
            xRoot = sets.find(x)
            yRoot = sets.find(y)
            output("yes\n" if xRoot == yRoot else "no\n")

    flush()
