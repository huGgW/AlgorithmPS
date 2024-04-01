import heapq
import math

class MedianFinder:

    def __init__(self):
        self.smallHeap = ReversedHeap()
        self.bigHeap = Heap()
        self.len = 0
        
    def addNum(self, num: int) -> None:
        self.len += 1

        # add element to right heap
        if len(self.smallHeap) == 0 or self.smallHeap.peek() >= num:
            self.smallHeap.push(num)
        else:
            self.bigHeap.push(num)

        # balance
        while len(self.smallHeap) > math.ceil(self.len / 2):
            elem = self.smallHeap.pop()
            self.bigHeap.push(elem)
        while len(self.bigHeap) > self.len // 2:
            elem = self.bigHeap.pop()
            self.smallHeap.push(elem)

    def findMedian(self) -> float:
        if self.len % 2 == 0:
            smallMed = self.smallHeap.peek()
            bigMed = self.bigHeap.peek()
            return (smallMed + bigMed) / 2
        else:
            return self.smallHeap.peek()
        

class Heap:
    def __init__(self):
        self.heap: List[int] = []

    def __len__(self):
        return len(self.heap)

    def push(self, x: int):
        heapq.heappush(self.heap, x)

    def pop(self) -> int:
        result = heapq.heappop(self.heap)
        return result

    def peek(self) -> int:
        return self.heap[0]


class ReversedHeap(Heap):
    def __init__(self):
        self.heap: List[int] = []

    def __len__(self):
        return len(self.heap)

    def push(self, x: int):
        heapq.heappush(self.heap, -x)

    def pop(self) -> int:
        result = -1 *heapq.heappop(self.heap)
        return result

    def peek(self) -> int:
        return -1 * self.heap[0]



# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()