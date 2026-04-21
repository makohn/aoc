package year2020

import util.core.*
import util.parse.*

class Day02 : Solution<Int, Int> {

    private class Input(
        val first: Int,
        val second: Int,
        val char: Char,
        val password: String,
    )

    private fun Input(line: String): Input {
        val (first, second, third) = line.splitAsciiWhitespace()
        val ints = first.extractInts()
        return Input(
            first = ints[0],
            second = ints[1],
            char = second[0],
            password = third,
        )
    }

    override fun part1(input: String): Int = input.lines().map(::Input).count { input ->
        with(input) {
            password.count { it == char } in first..second
        }
    }

    override fun part2(input: String): Int = input.lines().map(::Input).count { input ->
        with(input) {
            (password[first - 1] == char) xor (password[second - 1] == char)
        }
    }
}
