int maxSubArray(int *nums, int numsSize) {
  int sum = nums[0];
  int maxSum = sum;
  int beg = 0;
  int end = 0;

  for (int i = 1; i < numsSize; i++) {
    if (sum < 0) {
      beg = i;
      end = i;
      sum = nums[i];
    } else {
      end = i;
      sum += nums[end];
    }

    if (sum > maxSum) {
      maxSum = sum;
    }
  }

  return maxSum;
}
