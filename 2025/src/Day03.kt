import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            var max = -1
            var maxPos = -1
            for ((i, d) in line.withIndex()) {
                if (i == line.lastIndex) break
                val di = d.digitToInt()
                if (di > max) {
                    max = di
                    maxPos = i
                }
            }
            var secMax = -1
            for (d in line.substring(maxPos + 1)) {
                val di = d.digitToInt()
                if (di > secMax) {
                    secMax = di
                }
            }
            val res = max * 10 + secMax
            sum += res
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val m = 11
        var sum = 0L
        for (line in input) {
            val maximums = Array(12) { -1 to -1 }
            for (n in 0..m) {
                val startIndex = if (n > 0) maximums[n-1].second + 1 else 0
                for ((i, d) in line.substring(startIndex).withIndex()) {
                    if ((i + startIndex) > line.lastIndex - (m - n)) break
                    val di = d.digitToInt()
                    if (di > maximums[n].first) {
                        maximums[n] = di to i + startIndex
                    }
                }
            }
            val z = maximums.mapIndexed { i, p -> (p.first * 10.0.pow(m - i)).toLong() }.sum()
            sum += z
        }
        return sum
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}