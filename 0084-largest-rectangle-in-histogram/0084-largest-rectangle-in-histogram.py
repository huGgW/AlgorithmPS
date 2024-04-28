from bisect import bisect_left
import heapq

class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        maxArea = 0

        # heap for minimum height priority
        hp = [(h, i) for (i, h) in enumerate(heights)]
        heapq.heapify(hp)


        # for analyze avaialbe width for each height
        insert = [-1, len(heights)]
        while hp:
            h, i = heapq.heappop(hp)

            # find width, calc area
            iidx = bisect_left(insert, i)
            width = insert[iidx] - insert[iidx-1] - 1
            area = width * h

            # update insert list, maxArea
            insert.insert(iidx, i)
            maxArea = max(maxArea, area)

        return maxArea