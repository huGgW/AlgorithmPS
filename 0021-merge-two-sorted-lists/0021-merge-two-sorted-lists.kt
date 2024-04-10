/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    val maxVal = 101

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode?
        = if (list1 == null && list2 == null) {
            null
        } else {
            var node1 = list1
            var node2 = list2
            var mergeList: ListNode? = null
            var mergeNode: ListNode? = null

            while (node1 != null || node2 != null) {
                val val1 = node1?.`val` ?: maxVal
                val val2 = node2?.`val` ?: maxVal

                val newNode = if (val1 <= val2) {
                    node1 = node1!!.next
                    ListNode(val1)
                } else {
                    node2 = node2!!.next
                    ListNode(val2)
                }

                if (mergeList == null) {
                    mergeList = newNode
                    mergeNode = newNode
                } else {
                    mergeNode!!.next = newNode
                    mergeNode = newNode
                }
            }

            mergeList
        }
}