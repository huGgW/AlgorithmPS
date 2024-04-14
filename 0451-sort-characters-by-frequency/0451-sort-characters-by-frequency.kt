import java.util.*

class Solution {
    fun frequencySort(s: String): String {
        // cnt letters
        val cnts: MutableMap<Char, Int> = mutableMapOf()
        s.forEach {
            cnts[it] = cnts[it] ?.plus(1) ?: 1
        }

        // create heap
        val heap = PriorityQueue<Char> { c1, c2 ->
            (cnts[c2]!!.compareTo(cnts[c1]!!)).takeIf {
                it != 0
            } ?: c1.compareTo(c2)
        }
        heap.addAll(s.toList())

        // build sorted string
        val sb = StringBuilder()
        // heap.forEach {
        //     sb.append(it)
        // }
        while (heap.isNotEmpty()) {
            sb.append(heap.poll())
        }

        return sb.toString()
    }
}
