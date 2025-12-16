import kotlin.math.abs

class Day01 : Day<Int, Int>(year = 2024, day = 1) {

    override fun part1(input: String): Int {
        val lists = input
            .lines()
            .map { it.split(Regex("\\s+")).map { it.toInt() } }
            .let { lists -> lists.map { it[0] } to lists.map { it[1] } }
        return lists.first.sorted().zip(lists.second.sorted())
            .sumOf { pair -> abs(pair.first - pair.second) }
    }

    override fun part2(input: String): Int {
        val lists = input
            .lines()
            .map { it.split(Regex("\\s+")).map { it.toInt() } }
            .let { lists -> lists.map { it[0] } to lists.map { it[1] } }
        return lists.first.sumOf { it * lists.second.count { i -> i == it } }
    }
}

fun main() = Day01().run {
    println(part1(input))
    println(part2(input))
}