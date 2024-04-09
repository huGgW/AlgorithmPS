use std::collections::{BinaryHeap, HashMap};

#[derive(Ord, PartialOrd, Eq, PartialEq)]
struct CntEntry {
    cnt: i32,
    num: i32,
}

impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut cnts = HashMap::new();

        nums.iter().for_each(|n| {
            cnts.entry(n)
                .and_modify(|cnt_ref| *cnt_ref += 1)
                .or_insert(1);
        });

        let mut heap: BinaryHeap<CntEntry> =
            BinaryHeap::from_iter(cnts.iter().map(|(k, v)| CntEntry { num: **k, cnt: *v }));

        (0..k).map(|i| heap.pop().unwrap().num).collect()
    }
}
