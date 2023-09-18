class Solution {
    fun solution(N: Int, number: Int): Int {
        val maxCnt = 8
        var table = (1..maxCnt).map {
            mutableListOf<Int>()
        }

        if (number == N) {
            return 1
        }
        table[0].add(N)

        for (cnt in 2..maxCnt) {
            val i = cnt - 1
            val set = mutableSetOf<Int>()

            // First element is NNN...
            val nn = table[i-1][0] * 10 + N
            if (nn == number) {
                return cnt
            }
            table[i].add(nn)
            set.add(nn)

            // Make calcs from under tables
            (1..cnt/2).map { cntX ->
                val iX = cntX - 1
                val iY = (cnt - cntX) - 1

                table[iX].map { x ->
                    table[iY].map { y ->
                        val calcs = mutableListOf(
                                x + y,
                                x - y,
                                y - x,
                                x * y
                        )
                        if (y != 0) {
                            calcs.add(x / y)
                        }
                        if (x != 0) {
                            calcs.add(y / x)
                        }

                        calcs.forEach {
                            if (!set.contains(it)) {
                                if (it == number) {
                                    return cnt
                                }

                                set.add(it)
                                table[i].add(it)
                            }
                        }
                    }
                }
            }
        }

        return -1
    }
}
