from collections import deque

class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        dest = len(graph)-1

        paths: List[List[int]] = []
        queue: deque[List[int]] = deque()
        queue.append([0])

        while queue:
            tmpPath = queue.popleft()
            lastNode = tmpPath[-1]

            neighbors = graph[lastNode]
            for n in neighbors:
                newPath = tmpPath.copy() + [n]
                if n == dest:
                    paths.append(newPath)
                else:
                    queue.append(newPath)

        return paths