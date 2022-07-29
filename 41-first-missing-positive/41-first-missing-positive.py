class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        # 정규화 (가능하지 않은 숫자 다 -1로)
        for i in range(len(nums)):
            if nums[i] <= 0 or nums[i] > len(nums):
                nums[i] = -1

        # 각 원소를 라벨로 하여 건너가며 제거, 즉 0으로 변환 (꼬리물기)
        for i in range(len(nums)):
            next: int = nums[i]
            # 원소가 다음을 가리키면 다음 위치 저장, 아니면 -1
            if next > 0:
                store: int = next
            else:
                store: int = -1

            while store != -1:
                curr: int = store
                next = nums[curr-1]
                # 원소가 다음을 가리키면 다음 위치 저장
                if next > 0:
                    store = next
                else:
                    store = -1
                nums[curr-1] = 0

        # 훑으며 방문하지 않은 첫번째 구간 위치 반환
        for i in range(len(nums)):
            if nums[i] != 0:
                return i+1
        return len(nums)+1

# testcases
s: Solution = Solution()
assert s.firstMissingPositive([1, 2, 0]) == 3
assert s.firstMissingPositive([3, 4, -1, 1]) == 2
assert s.firstMissingPositive([7, 8, 9, 11, 12]) == 1
assert s.firstMissingPositive([1, 1]) == 2