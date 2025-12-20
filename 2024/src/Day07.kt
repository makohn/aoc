import aoc.string.extractLongs

class Day07 : Day<Long, Long>(year = 2024, day = 7) {

    override fun part1(input: String): Long {
        var sum = 0L
        for (line in input.lines()) {
            val values = line.extractLongs()
            val testValue = values[0]
            val numbers = values.drop(1)
            val results = ArrayList<Long>()
            results.add(numbers[0])
            outer@ for (i in 1..<numbers.size) {
                val res = ArrayList(results)
                results.clear()
                for (r in res) {
                    val add = r + numbers[i]
                    val mul = r * numbers[i]
                    if (i == numbers.lastIndex && (add == testValue || mul == testValue)) {
                        sum += testValue
                        break@outer
                    }
                    if (add <= testValue) results.add(add)
                    if (mul <= testValue) results.add(mul)
                }
            }
        }
        return sum
    }

    private fun Long.concat(other: Long) = (this.toString() + other.toString()).toLong()

    override fun part2(input: String): Long {
        var sum = 0L
        for (line in input.lines()) {
            val values = line.extractLongs()
            val testValue = values[0]
            val numbers = values.drop(1)
            val results = ArrayList<Long>()
            results.add(numbers[0])
            outer@ for (i in 1..<numbers.size) {
                val res = ArrayList(results)
                results.clear()
                for (r in res) {
                    val add = r + numbers[i]
                    val mul = r * numbers[i]
                    val concat = r.concat(numbers[i])
                    if (i == numbers.lastIndex && (add == testValue || mul == testValue || concat == testValue)) {
                        sum += testValue
                        break@outer
                    }
                    if (add <= testValue) results.add(add)
                    if (mul <= testValue) results.add(mul)
                    if (concat <= testValue) results.add(concat)
                }
            }
        }
        return sum
    }
}

fun main() = Day07().run {
    println(part1(input))
    println(part2(input))
}