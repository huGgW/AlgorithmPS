import sys
input = sys.stdin.readline
output = sys.stdout.write
flush = sys.stdout.flush
sys.setrecursionlimit(10**6)

def main():
    # read from input
    N, M = map(int, input().split())
    orders: list[list[int]] = []
    for _ in range(M):
        order = list(map(int, input().split()))[1:]
        orders.append(order)

    # create graph
    graph: list[set[int]] = [set() for _ in range(N+1)]
    cnts: list[int] = [0 for _ in range(N+1)]
    for order in orders:
        for i in range(1, len(order)):
            if order[i] not in graph[order[i-1]]:
                graph[order[i-1]].add(order[i])
                cnts[order[i]] += 1

    # topological sort
    queue = list(filter(lambda i: cnts[i] == 0, range(1, N+1)))
    result: list[int] = []
    while queue:
        node = queue.pop(0)
        result.append(node)

        edges = graph[node]
        for edge in edges:
            cnts[edge] -= 1
            if cnts[edge] == 0:
                queue.append(edge)


    if len(result) < N: # Not possible
        output("0\n")
    else:
        for r in result:
            output(f"{r}\n")
    flush()


if __name__ == "__main__":
    main()