class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        prereqCnt = {k: 0 for k in range(numCourses)}
        graph = {k: [] for k in range(numCourses)}

        for (p, s) in prerequisites:
            graph[p].append(s)
            prereqCnt[s] += 1

        while True:
            start: Optional[int] = None
            for (i, c) in prereqCnt.items():
                if c == 0:
                    start = i
                    break

            if start is None:
                break

            for s in graph[start]:
                if s in prereqCnt:
                    prereqCnt[s] -= 1
            del graph[start]
            del prereqCnt[start]

        return len(prereqCnt) == 0