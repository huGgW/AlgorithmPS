impl Solution {
    pub fn three_sum(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums.clone();
        nums.sort_unstable();

        let mut ans = vec![];

        let mut i = 0;
        while i <= nums.len() - 3 {
            let mut j = i + 1;
            let mut k = nums.len();
            while j <= nums.len() - 2 && j + 1 < k {
                let need_val = -(nums[i] + nums[j]);
                let search_result = nums[j + 1..k].binary_search(&need_val);

                match search_result {
                    Ok(idx) => {
                        k = idx + (j + 1);
                        ans.push(vec![nums[i], nums[j], nums[k]]);
                    }
                    Err(idx) => {
                        k = idx + (j + 1);
                    }
                }

                j += nums[j..].partition_point(|&x| x <= nums[j]);
            }

            i += nums[i..].partition_point(|&x| x <= nums[i]);
        }

        return ans;
    }
}
