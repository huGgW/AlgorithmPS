class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val edges = mutableMapOf<Int, MutableSet<Int>>().apply {
            (0..<numCourses).forEach {
                this[it] = mutableSetOf()
            }
        }
        val ins = IntArray(numCourses)

        prerequisites.forEach { (u, v) ->
            if (v !in edges[u]!!) {
                edges[u]!!.add(v)
                ins[v]++
            }
        }

        val next = ArrayDeque<Int>().apply {
            addAll(ins.withIndex().filter { (_, c) -> c == 0 }.map { (i, _) -> i })
        }
        while (next.isNotEmpty()) {
            val p = next.removeFirst()
            val neighbors = edges[p]!!
            edges[p]!!.forEach {
                ins[it]--
                if (ins[it] == 0) {
                    next.add(it)
                }
            }
        }

        return ins.all { it == 0 }
    }
}
