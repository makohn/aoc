import aoc.string.extractLongs

class Day11 : Day<Long, Long>(year = 2024, day = 11) {

    private fun MutableMap<Long, Long>.addTo(k: Long, v: Long) {
        put(k, getOrDefault(k, 0) + v)
    }

    private fun solve(inp: String, blinks: Int): Long {
        var numberCounts = inp.extractLongs().associateWith { 1L }.toMutableMap()
        repeat(blinks) {
            val counts = HashMap<Long, Long>()
            for ((n, c) in numberCounts) {
                if (n == 0L) {
                    counts.addTo(1, c)
                    continue
                }
                var x = 10L
                var d = 1
                var p = 1L
                while (n >= x) {
                    x *= 10
                    d++
                    if (d % 2 == 0) p *= 10
                }
                if (d % 2 == 0) {
                    counts.addTo(n / p, c)
                    counts.addTo(n % p, c)
                    continue
                }
                counts.addTo(n * 2024, c)
            }
            numberCounts = counts
        }
        return numberCounts.values.sum()
    }

    override fun part1(input: String): Long {
        return solve(input, 25)
    }

    override fun part2(input: String): Long {
        return solve(input, 75)
    }
}

fun main() = Day11().run {
    println(part1(input))
    println(part2(input))
}