package year2021

import util.Solution

class Day24 : Solution<Long, Long>(year = 2021, day = 24) {

    override fun part1(input: String): Long {
        return findSerialNumber(input)
    }

    override fun part2(input: String): Long {
        return findSerialNumber(input, smallest = true)
    }

    private fun findSerialNumber(input: String, smallest: Boolean = false): Long {
        val number = IntArray(14)
        val currentNumbers = mutableListOf<Pair<Int, Int>>()
        val instructions = input.lines().map { it.split(" ") }
        for (i in 0 until 14) {
            val x = instructions[18*i + 5][2].toInt()
            val y = instructions[18*i + 15][2].toInt()
            if (x > 0) {
                currentNumbers.add(0, (y to i))
            } else {
                val (y1, pos) = currentNumbers.removeFirst()
                var j = if (smallest) 1 else 9
                if (smallest) {
                    while (j + y1 + x < 1) j++
                } else {
                    while (j + y1 + x > 9) j--
                }
                number[pos] = j
                number[i] = j + y1 + x
            }
        }
        return number.joinToString("").toLong()
    }
}

fun main() = Day24().run {
    println(part1(input))
    println(part2(input))
}