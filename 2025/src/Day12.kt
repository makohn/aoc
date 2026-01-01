import util.parse.extractInts

class Day12 : Day<Int, Int>(year = 2025, day = 12) {

    override fun part1(input: String) = input
        .split("\n\n")
        .last()
        .lines()
        .map { line -> line.extractInts() }
        .count { ints -> (ints[0] / 3) * (ints[1] / 3) >= ints.drop(2).sum() }

    override fun part2(input: String) = 0
}

fun main() = Day12().run {
    println(part1(input))
}