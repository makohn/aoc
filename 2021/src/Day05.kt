class Day05 : Day<Int, Int>(year = 2021, day = 5) {

    override fun part1(input: String): Int {
        val instructions = parse(input)
        return instructions
            .filter { it.first.first == it.second.first || it.first.second == it.second.second }
            .flatMap { (it.first .. it.second) }
            .groupingBy { it }.eachCount()
//            .also { visualize(it) }
            .filter { it.value >= 2 }.count()
    }

    override fun part2(input: String): Int {
        val instructions = parse(input)
        return instructions
            .flatMap { (it.first .. it.second) }
            .groupingBy { it }.eachCount()
//            .also { visualize(it) }
            .filter { it.value >= 2 }.count()
    }

    /**
     * x1 < x2 && y1 < y2
     * x1 < x2 && y1 > y2
     * x1 < x2 && y1 = y2
     *
     * x1 > x2 && y1 < y2
     * x1 > x2 && y1 > y2
     * x1 > x2 && y1 = y2
     *
     * x1 = x2 && y1 < y2
     * x1 = x2 && y1 > y2
     * x1 = x2 && y1 = y2
     *
     */
    private infix operator fun Pair<Int, Int>.rangeTo(other: Pair<Int, Int>) = generateSequence(this) {
        if (it.first < other.first && it.second < other.second) it.first + 1 to it.second + 1
        else if (it.first < other.first && it.second > other.second) it.first + 1 to it.second - 1
        else if (it.first < other.first) it.first + 1 to it.second

        else if (it.first > other.first && it.second < other.second) it.first -1 to it.second + 1
        else if (it.first > other.first && it.second > other.second) it.first - 1 to it.second - 1
        else if (it.first > other.first) it.first - 1 to it.second

        else if (it.second < other.second) it.first to it.second + 1
        else if (it.second > other.second) it.first to it.second - 1
        else null
    }

    private fun parse(input: String) = input
        .lines()
        .flatMap { it.split(" -> ").flatMap { p -> p.split(",") } }
        .asSequence().map { it.toInt() }
        .chunked(2).map { it.first() to it.last() }
        .chunked(2).map { it.first() to it.last() }
        .toList()

    private fun visualize(map: Map<Pair<Int, Int>, Int>) {
        val minX = map.minOf { it.key.first }
        val maxX = map.maxOf { it.key.first }
        val minY = map.minOf { it.key.second }
        val maxY = map.maxOf { it.key.second }
        (minY .. maxY).forEach { y ->
            (minX .. maxX).forEach { x ->
                val point = x to y
                if (point in map.keys) print(map[point])
                else print(".")
            }
            println()
        }
    }
}

fun main() = Day05().run {
    println(part1(input))
    println(part2(input))
}