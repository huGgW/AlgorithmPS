import java.io.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    
    // Read N, S
    val lineNums = reader.readLine()
        .trim()
        .split(" ")
        .map(String::toInt)
    
    val N = lineNums[0]
    val S = lineNums[1]
    
    val nums = reader.readLine()
        .trim()
        .split(" ")
        .map(String::toInt)
    
    reader.close()
    
    var len = 0
    var from = 0
    var to = 0
    var sum = nums[0]
    while (true) {
        if (sum >= S) {
            if (len == 0 || len > (to - from) + 1) {
                len = (to - from) + 1
            }
            sum -= nums[from]
            from += 1
        } else {
            if (to == N-1) {
                break
            }
            to += 1
            sum += nums[to]
        }
    }
    
    println(len)
}