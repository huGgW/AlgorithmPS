package org.example

import java.util.*
import java.io.*
import kotlin.random.Random

data class Diamond (
    val weight: Int,
    val cost: Int,
)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.`out`))

    val (n, k) = reader.readLine().split(" ").map { it.toInt() }

    val diamondHeap = PriorityQueue<Diamond>(n) { d1, d2 -> 
        if (d1.cost == d2.cost) {
            d1.weight - d2.weight
        } else {
            d2.cost - d1.cost
        }
    }
    (0 until n).map {
        val (w, c) = reader.readLine().split(" ").map { it.toInt() }
        Diamond(w, c)
    }.let {
        diamondHeap.addAll(it)
    }

    val bagTree = TreeSet<Int>()
    val bagTrack = mutableMapOf<Int, Int>()
    repeat(k) {
        val b = reader.readLine().toInt()
        bagTree.add(b)
        bagTrack[b] = bagTrack.getOrDefault(b, 0) + 1
    }


    val result = solution(diamondHeap, bagTree, bagTrack)
    writer.write("${result}\n")

    writer.flush()
    reader.close()
    writer.close()
}

fun solution(diamondHeap: PriorityQueue<Diamond>, bagTree: TreeSet<Int>, bagTrack: MutableMap<Int, Int>): Long {
    var totalSum = 0L

    while (diamondHeap.isNotEmpty()) {
        val diamond = diamondHeap.poll()

        bagTree.ceiling(diamond.weight)?.let {
            totalSum += diamond.cost
            bagTrack[it] = bagTrack[it]!!.minus(1)
            if (bagTrack[it] == 0) {
                bagTree.remove(it)
            }
        }
    }

    return totalSum
}
