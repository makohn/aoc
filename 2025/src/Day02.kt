import kotlin.math.pow

class Day02 : Day<Long, Long>(year = 2025, day = 2) {

    override fun part1(input: String): Long {
        val ranges = input.split(",")
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

    override fun part2(input: String): Long {
        val ranges = input.split(",")
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
}

fun main() = Day02().run {
    println(part1(input))
    println(part2(input))
}