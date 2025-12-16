import kotlin.math.abs

class Day07 : Day<Int, Int>(year = 2021, day = 7) {

    override fun part1(input: String): Int {
        val positions = parse(input)
        return minimizeCost(positions) { goal ->
            positions.sumOf { pos -> abs(pos - goal) }
        }
    }

    override fun part2(input: String): Int {
        val positions = parse(input)
        return minimizeCost(positions) { goal ->
            positions.sumOf { pos ->
                var sum = 0
                for (j in (1 .. abs(pos - goal))) sum += j
                sum
            }
        }
    }

    private fun parse(input: String) = input.lines().first().split(",").map { it.toInt() }

    private fun minimizeCost(positions: List<Int>, costFunction: (Int) -> Int): Int {
        val costs = mutableListOf<Int>()
        for (goal in (1..positions.maxOf { it })) {
            costs.add(costFunction.invoke(goal))
        }
        return costs.minOf { it }
    }
}

fun main() = Day07().run {
    println(part1(input))
    println(part2(input))
}