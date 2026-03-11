package year2024

import util.core.*
import util.parse.extractLongs

class Day22 : Solution<Long, Int>(year = 2024, day = 22) {

    override fun part1(input: String): Long {
        val initialNumbers = input.extractLongs()
        var sum = 0L
        for (number in initialNumbers) {
            var secretNumber = number
            repeat(2000) {
                val a = secretNumber * 64
                secretNumber = secretNumber xor a
                secretNumber %= 16777216

                val b = secretNumber / 32
                secretNumber = secretNumber xor b
                secretNumber %= 16777216

                val c = secretNumber * 2048
                secretNumber = secretNumber xor c
                secretNumber %= 16777216
            }
            sum += secretNumber
        }
        return sum
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day22().run {
    println(part1(input))
}