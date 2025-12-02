import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Long {
        val ranges = input.joinToString("").split(",")
        var sum = 0L
        for (range in ranges) {
            val (a, b) = range.split("-")
            for (id in a.toLong() .. b.toLong()) {
                val pow = 10.0.pow(id.toString().length / 2).toLong()
                val div = id.div(pow)
                val rem = id.rem(pow)
                if (div == rem) sum += id
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val ranges = input.joinToString("").split(",")
        var sum = 0L
        for (range in ranges) {
            val (a, b) = range.split("-")
            nextId@for (id in a.toLong() .. b.toLong()) {
                val len = id.toString().length
                for (step in 1..len / 2) {
                    val chunks = id.toString().chunked(step)
                    if (chunks.all { it == chunks[0] }) {
                        sum += id
                        continue@nextId
                    }
                }
            }
        }
        return sum
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}