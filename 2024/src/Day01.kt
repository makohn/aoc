import kotlin.math.abs

fun main() {

    val day = "01"

    fun part1(input: List<String>): Int {
        val lists = input
            .map { it.split(Regex("\\s+")).map { it.toInt() } }
            .let { lists -> lists.map { it[0] } to lists.map { it[1] } }
        return lists.first.sorted().zip(lists.second.sorted())
            .sumOf { pair -> abs(pair.first - pair.second) }
    }

    fun part2(input: List<String>): Int {
        val lists = input
            .map { it.split(Regex("\\s+")).map { it.toInt() } }
            .let { lists -> lists.map { it[0] } to lists.map { it[1] } }
        return lists.first.sumOf { it * lists.second.count { i -> i == it }  }
    }

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}