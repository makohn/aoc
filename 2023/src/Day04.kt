class Day04 : Day<Int, Int>(year = 2023, day = 4) {

    val numberRegex = Regex("\\d+")

    fun String.numbers() = numberRegex.findAll(this).map { it.value.toInt() }.toList()

    override fun part1(input: String): Int {
        return input
            .lines()
            .map { it.split("|") }
            .map { (a, b) -> a.substringAfter(":").numbers() to b.numbers() }
            .sumOf { (winning, numbers) ->
                numbers.filter { it in winning }.fold(0) { acc: Int, _ -> if (acc == 0) 1 else acc * 2 }
            }
    }

    override fun part2(input: String): Int {
        val parsed = input
            .lines()
            .map { it.split("|") }
            .map { (a, b) -> a.substringAfter(":").numbers() to b.numbers() }

        val winning = parsed.map { it.first }.toList()
        val cards = parsed.map { it.second }.toList()
        val cardCount = IntArray(cards.size) { 1 }

        for ((i, card) in cards.withIndex()) {
            val points = card.count { it in winning[i] }
            for (j in 1..points) {
                cardCount[i+j] += cardCount[i]
            }
        }
        return cardCount.sum()
    }
}

fun main() = Day04().run {
    println(part1(input))
    println(part2(input))
}