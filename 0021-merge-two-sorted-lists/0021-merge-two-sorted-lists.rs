// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
// 
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn merge_two_lists(
        list1: Option<Box<ListNode>>,
        list2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        match (list1, list2) {
            (Option::None, Option::None) => Option::None,
            (Option::Some(nb), Option::None) => Option::Some(Box::new(ListNode {
                val: nb.val,
                next: (Solution::merge_two_lists(nb.next, Option::None)),
            })),
            (Option::None, Option::Some(nb)) => Option::Some(Box::new(ListNode {
                val: nb.val,
                next: (Solution::merge_two_lists(Option::None, nb.next)),
            })),
            (Option::Some(nb1), Option::Some(nb2)) => {
                if nb1.val <= nb2.val {
                    Option::Some(Box::new(ListNode {
                        val: nb1.val,
                        next: (Solution::merge_two_lists(nb1.next, Option::Some(nb2))),
                    }))
                } else {
                    Option::Some(Box::new(ListNode {
                        val: nb2.val,
                        next: (Solution::merge_two_lists(Option::Some(nb1), nb2.next)),
                    }))
                }
            }
        }
    }
}
