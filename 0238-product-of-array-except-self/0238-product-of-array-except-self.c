/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* productExceptSelf(int* nums, int numsSize, int* returnSize) 
{
    int* ans = malloc(sizeof(int) * numsSize);
    memcpy(ans, nums, sizeof(int) * numsSize);

    for (int i = numsSize-2; i >= 0; i--) 
    {
        nums[i] *= nums[i+1];
    }

    int multiplier = 1;
    for (int i = 0; i <= numsSize-2; i++) 
    {
        int curr_val = ans[i];
        ans[i] = multiplier * nums[i+1];
        multiplier *= curr_val;
    }
    ans[numsSize-1] = multiplier;

    *returnSize = numsSize;
    return ans;
}