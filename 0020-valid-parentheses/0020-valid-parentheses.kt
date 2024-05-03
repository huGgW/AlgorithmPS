class Solution {
    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        s.forEach {
            when (it) {
                in setOf('(', '{', '[') -> stack.addLast(it)
                ')' -> if (stack.lastOrNull() == '(') {
                    stack.removeLast()
                } else {
                    return false
                }
                '}' -> if (stack.lastOrNull() == '{') {
                    stack.removeLast()
                } else {
                    return false
                }
                ']' -> if (stack.lastOrNull() == '[') {
                    stack.removeLast()
                } else {
                    return false
                }
                else -> return false
            }
        }
        return stack.isEmpty()
    }
}
