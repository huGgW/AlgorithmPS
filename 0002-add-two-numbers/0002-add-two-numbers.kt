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
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var node1 = l1
        var node2 = l2
        var toAdd = 0
        var sumList: ListNode? = null
        var node: ListNode? = null

        while (node1 != null || node2 != null) {
            var sumOfNode = (node1?.`val` ?: 0) + (node2?.`val` ?: 0) + toAdd
            if (sumOfNode >= 10) {
                sumOfNode -= 10
                toAdd = 1
            } else {
                toAdd = 0
            }

            val tmpNode = ListNode(sumOfNode)
            if (sumList == null) {
                sumList = tmpNode
            } else {
                node!!.next = tmpNode
            }

            node = tmpNode
            node1 = node1?.next
            node2 = node2?.next
        }

        if (toAdd == 1) {
            node!!.next = ListNode(toAdd)
        }

        return sumList
    }
}