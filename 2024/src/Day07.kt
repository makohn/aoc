import aoc.string.extractLongs

class Day07 : Day<Long, Long>(year = 2024, day = 7) {

    override fun part1(input: String): Long {
        var sum = 0L
        for (line in input.lines()) {
            val values = line.extractLongs()
            val testValue = values[0]
            val numbers = values.drop(1)
            fun solve(n: Long, i: Int): Boolean = when {
                i == numbers.size -> n == testValue
                n > testValue -> false
                solve(n + numbers[i], i + 1) -> true
                solve(n * numbers[i], i + 1) -> true
                else -> false
            }

            if(solve(numbers[0], 1)) sum += testValue
        }
        return sum
    }

    private fun Long.concat(other: Long): Long {
        var o = other
        var t = this
        while (o > 0) {
            t *= 10
            o /= 10
        }
        return t + other
    }

    override fun part2(input: String): Long {
        var sum = 0L
        for (line in input.lines()) {
            val values = line.extractLongs()
            val testValue = values[0]
            val numbers = values.drop(1)

            fun solve(n: Long, i: Int): Boolean = when {
                i == numbers.size -> n == testValue
                n > testValue -> false
                solve(n + numbers[i], i + 1) -> true
                solve(n * numbers[i], i + 1) -> true
                solve(n.concat(numbers[i]), i + 1) -> true
                else -> false
            }

            if(solve(numbers[0], 1)) sum += testValue
        }
        return sum
    }
}

fun main() = Day07().run {
    println(part1(input))
    println(part2(input))
}