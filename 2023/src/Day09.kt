class Day09 : Day<Int, Int>(year = 2023, day = 9) {

    fun predictLast(history: List<Int>): Int {
        if (history.all { it == 0 }) return 0
        return history.last() + predictLast(history.zipWithNext { a, b -> b - a })
    }

    fun predictFirst(history: List<Int>): Int {
        if (history.all { it == 0 }) return 0
        return history.first() - predictFirst(history.zipWithNext { a, b -> b - a })
    }

    override fun part1(input: String) = input
        .lines()
        .map { it.split(" ").map { it.toInt() } }
        .sumOf { predictLast(it) }

    override fun part2(input: String) = input
        .lines()
        .map { it.split(" ").map { it.toInt() } }
        .sumOf { predictFirst(it) }
}

fun main() = Day09().run {
    println(part1(input))
    println(part2(input))
}