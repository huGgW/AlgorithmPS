/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type State struct {
    node *TreeNode
    level int
}

func levelOrder(root *TreeNode) [][]int {
    var queue []State
    var result [][]int

    if root != nil {
        queue = append(queue, State{root, 0})
    }

    for len(queue) > 0 {
        state := queue[0]
        node, level := state.node, state.level
        queue = queue[1:]

        if len(result) == level {
            result = append(result, []int{})
        }
        result[level] = append(result[level], node.Val)

        if node.Left != nil {
            queue = append(queue, State{node.Left, level+1})
        }
        if node.Right != nil {
            queue = append(queue, State{node.Right, level+1})
        }
    }

    return result
}