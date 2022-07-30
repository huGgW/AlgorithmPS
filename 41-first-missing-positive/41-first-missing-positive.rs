impl Solution {
    pub fn first_missing_positive(nums: Vec<i32>) -> i32 {
        let mut nums = nums.clone();
        let n = nums.len();
        // make numbers which is out of range to -1
        for x in nums.iter_mut() {
            *x = {
                if *x <= 0 || *x > (n as i32) {
                    -1
                }
                else { *x }
            };
        }

        // use each elements as a postional index, make elements as 0
        for i in 0..n {
            let mut next = *nums.get(i).unwrap();
            // if element is point to next then store next position, or store -1
            let mut store = if next > 0 { next } else { -1 };

            while store != -1 {
                let curr = store;
                next = *nums.get((curr - 1) as usize).unwrap();
                // if element points next then store next position
                store = if next > 0 { next } else { -1 };
                *nums.get_mut((curr-1) as usize).unwrap() = 0;
            }
        }

        // return first postition which didn't checked (nonzero)
        for (i, x) in nums.iter().enumerate() {
            if *x != 0 { return (i+1) as i32; }
        }
        (n + 1) as i32
    }
}