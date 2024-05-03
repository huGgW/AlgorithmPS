class MinStack() {
    val stack = ArrayDeque<Int>()
    val orders = mutableListOf<Int>()

    fun push(v: Int) {
        stack.add(v)

        val insertIdx = orders.binarySearch(v).let {
            if (it < 0) {
                -(it + 1)
            } else {
                it
            }
        }
        orders.add(insertIdx, v)
    }

    fun pop() {
        val v = stack.removeLastOrNull()
        v?.let {
            val idx = orders.binarySearch(it)
            orders.removeAt(idx)
        }
    }

    fun top() = stack.last()

    fun getMin() = orders.first()
}


/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(`val`)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */