import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val result = (
        reader.readLine()
            .split(' ')
            .map { it.toInt() }
            .fold(0) { t, a -> t + (a * a) }
        ) % 10

    writer.write("$result\n")
    writer.flush()
}
