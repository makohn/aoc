class Day01 : Day<Int, Int>(year = 2023, day = 1) {

    val digits = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun String.replace(mapping: Map<String, String>): String {
        var str = this
        mapping.forEach { str = str.replace(it.key, it.key.first() + it.value + it.key.last()) }
        return str
    }

    override fun part1(input: String) = input
        .lines()
        .map { Regex("\\d").findAll(it).map { it.value } }
        .sumOf { (it.first() + it.last()).toInt() }

    override fun part2(input: String) = input
        .lines()
        .map { it.replace(digits) }
        .map { Regex("\\d").findAll(it).map { it.value } }
        .sumOf { (it.first() + it.last()).toInt() }
}

fun main() = Day01().run {
    println(part1(input))
    println(part2(input))
}