package year2025

import util.Solution

class Day01 : Solution<Int, Int>(year = 2025, day = 1) {

    override fun part1(input: String): Int {
        var dial = 50
        var c = 0
        for (ins in input.lines()) {
            val (d, n) = ins.take(1) to ins.drop(1).toInt()
            dial = if (d == "R") (dial + n).mod(100) else (dial - n).mod(100)
            if (dial == 0) c++
        }
        return c
    }

    override fun part2(input: String): Int {
        var dial = 50
        var c = 0
        for (ins in input.lines()) {
            val (d, n) = ins.take(1) to ins.drop(1).toInt()
            val dl = if (d == "R") (dial + n) else (dial - n)
            c += if (dl > 0) dl / 100 else (-dl / 100) + if(dial == 0) 0 else 1
            dial = dl.mod(100)
        }
        return c
    }
}

fun main() = Day01().run {
    println(part1(input))
    println(part2(input))
}