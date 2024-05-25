class Solution {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val pq = PriorityQueue<IntArray> { a, b ->
            ((a[0] * a[0]) + (a[1] * a[1])) - ((b[0] * b[0]) + (b[1] * b[1]))
        }.apply { addAll(points) }

        val ans = mutableListOf<IntArray>()
        repeat(k) {
            ans.add(pq.poll())
        }

        return ans.toTypedArray()
    }
}
