/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

const (
	START = iota
	INSERT
)

type Task struct {
	COMMAND uint
	Node *TreeNode
}

func inorderTraversal(root *TreeNode) []int {
	result := []int{}
	stack := []Task{Task{START, root}}

	for len(stack) > 0 {
		task := stack[len(stack)-1]
		stack = stack[:len(stack)-1]

		switch(task.COMMAND) {
		case START: {
            if task.Node == nil {
                break
            }
			stack = append(
				stack,
				[]Task{
					Task{START, task.Node.Right},
					Task{INSERT, task.Node},
					Task{START, task.Node.Left},
				}...
			)
		}
		case INSERT: {
			result = append(result, task.Node.Val)
		}
		}
	}

	return result
}
