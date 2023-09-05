class Solution {
    fun solution(numbers: IntArray): String {
        val numberStrings = numbers.map {
            it.toString()
        }

        val numberLens = numberStrings.map {
            it.length
        }

        val indexes = (0 until numbers.size).toMutableList()
        indexes.sortWith { i, j ->
            compare(numbers[i], numbers[j], numberLens[i], numberLens[j])
        }

        var answer = ""
        indexes.forEach {
            answer += numberStrings[it]
        }
        
        return changeZerosToZero(answer)
    }

    fun compare(
            a: Int, b: Int,
            aLen: Int, bLen: Int,
    ): Int {
        val aAndB = a * (Math.pow(10.0, bLen.toDouble()).toInt()) + b
        val bAndA = b * (Math.pow(10.0, aLen.toDouble()).toInt()) + a

        return -1 * (aAndB - bAndA)
    }

    fun changeZerosToZero(ans: String): String {
        val isOnlyZeros = false

        ans.forEach {
            if (it != '0') {
                return ans
            }
        }

        return "0"
    }
}
