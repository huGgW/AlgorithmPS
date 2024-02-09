import java.io.*

data class State (
        val index: Int = 0,
        val cost: Int,
)


data class Result (
        val maxMemory: Long,
        val bitmask: Bitmask,
)

typealias Bitmask = Int
infix fun Bitmask.contains(idx: Int) = this and (1 shl idx) > 0
infix fun Bitmask.mask(idx: Int) = this or (1 shl idx)
infix fun Bitmask.unmask(idx: Int) = this and (1 shl idx).inv()
infix fun Bitmask.union(other: Bitmask) = this or other
fun emptyBitmask(): Bitmask = 0

typealias Memo = MutableMap<State, Result>
fun Memo.fillMaxMemoryOfIdxCost(state: State, mems: List<Int>, costs: List<Int>) {
    if (this.contains(state)) {
        return
    }

    if (state.index == mems.size - 1) {
        this[state] = if (state.cost >= costs[state.index]) {
            Result(mems[state.index].toLong(), emptyBitmask() mask state.index)
        } else {
            Result(0, emptyBitmask())
        }

        return
    }

    // exclude
    val excludeState = State(state.index + 1, state.cost)
    fillMaxMemoryOfIdxCost(excludeState, mems, costs)
    val excludeResult = this[excludeState]!!

    // include
    val includeResult = if (state.cost < costs[state.index]) {
        excludeResult
    } else {
        val includeState = State(state.index + 1, state.cost - costs[state.index])
        fillMaxMemoryOfIdxCost(includeState, mems, costs)
        this[includeState]!!.let {
            Result(it.maxMemory + mems[state.index], it.bitmask mask state.index)
        }
    }

    this[state] = if (includeResult.maxMemory >= excludeResult.maxMemory) {
        includeResult
    } else {
        excludeResult
    }
}

fun binarySearchCost(searchMem: Long, smallCost: Int, bigCost: Int, memo: Memo, mems: List<Int>, costs: List<Int>): Int {
    if (smallCost > bigCost) {
        memo.fillMaxMemoryOfIdxCost(State(cost = smallCost), mems, costs)
        return smallCost
    }

    val midCost = (smallCost + bigCost) / 2
    val midState = State(cost = midCost)
    memo.fillMaxMemoryOfIdxCost(midState, mems, costs)

    return memo[midState]!!.let {
        if (it.maxMemory < searchMem) {
            binarySearchCost(searchMem, midCost + 1, bigCost, memo, mems, costs)
        } else if (searchMem < it.maxMemory) {
            binarySearchCost(searchMem, smallCost, midCost - 1, memo, mems, costs)
        } else {
            midCost
        }
    }
}

fun main() {
    // Init from input
    val reader = BufferedReader(
            InputStreamReader(System.`in`)
    )
    val (N, M) = reader.readLine().trim().split(" ").
            map ( String::toInt )
    val mems = reader.readLine().trim().split(" ").
            map ( String::toInt )
    val costs = reader.readLine().trim().split(" ").
            map ( String::toInt )
    reader.close()

    val memo: Memo = mutableMapOf()

    var minCost = costs.min()
    var cost = costs.sum()
    while (true) {
        cost = binarySearchCost(M.toLong(), minCost, cost, memo, mems, costs)
        val beforeState = State(cost = cost-1)

        memo.fillMaxMemoryOfIdxCost(beforeState, mems, costs)
        if (memo[beforeState]!!.maxMemory < M) {
            break
        }
    }

//    val realCost = costs.filterIndexed {idx, _ ->
//        possibleResult.bitmask contains idx
//    }.sum()
    println(cost)
}

