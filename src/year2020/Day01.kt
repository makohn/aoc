package year2020

import util.core.*
import util.parse.extractInts

class Day01 : Solution<Int, Int>(year = 2020, day = 1) {

    override fun part1(input: String): Int {
        val numbers = input.extractInts()
        for (i in 0..<numbers.size) for (j in i + 1..<numbers.size) {
            val a = numbers[i]
            val b = numbers[j]
            if (a + b == 2020) return a * b
        }
        error("Unreachable")
    }

    override fun part2(input: String): Int {
        val numbers = input.extractInts()
        for (i in 0..<numbers.size) for (j in i + 1..<numbers.size) for (k in j + 1..<numbers.size) {
            val a = numbers[i]
            val b = numbers[j]
            val c = numbers[k]
            if (a + b + c == 2020) return a * b * c
        }
        error("Unreachable")
    }
}