from typing import *
import sys
sys.setrecursionlimit(10**6)


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        # handle for single elem
        if len(nums) == 1:
            return 0 if nums[0] == target else -1

        # find real start
        start = self.find_start(0, len(nums)-1, nums)
        encoder = lambda i: i + len(nums) if i < start else i
        decoder = lambda i: i % len(nums)

        # binary search with encoder
        result = self.binary_search_encoded(
            target, nums, start, encoder(start-1), decoder
        )

        return result

    def find_start(self, begin: int, end: int, nums: List[int]) -> int:
        print(f"begin: {begin}, end: {end}")

        # if one or two elems, just find it
        if end - begin <= 1:
            return begin if nums[begin] < nums[end] else end

        mid = (begin + end) // 2
        if nums[begin] > nums[mid]:
            return self.find_start(begin, mid, nums)
        elif nums[mid] > nums[end]:
            return self.find_start(mid, end, nums)
        else:
            return begin

    def binary_search_encoded(
            self,
            target: int,
            nums: List[int],
            encodedBegin: int,
            encodedEnd: int,
            decoder: Callable[[int], int],
    ) -> int:
        if encodedBegin > encodedEnd:
            return -1

        encodedMid = (encodedBegin + encodedEnd) // 2
        x = nums[decoder(encodedMid)]

        if x < target:
            return self.binary_search_encoded(
                target, nums, encodedMid+1, encodedEnd, decoder
            )
        elif x > target:
            return self.binary_search_encoded(
                target, nums, encodedBegin, encodedMid-1, decoder
            )
        else:
            return decoder(encodedMid)
