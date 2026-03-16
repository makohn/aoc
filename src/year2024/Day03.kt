package year2024

import util.core.*

class Day03 : Solution<Int, Int>(year = 2024, day = 3) {

    private fun solve(input: String, ignoreEnabled: Boolean): Int {
        var i = 0
        fun nextNumber(): Int {
            var n = 0
            while (input[i].isDigit()) {
                n = 10 * n + input[i].digitToInt()
                i++
            }
            return n
        }

        var ans = 0
        var enabled = true
        while (i < input.length) {
            when {
                input[i] != 'm' && input[i] != 'd' -> {
                    i++
                    continue
                }
                input.startsWith("mul(", i) -> i += 4
                input.startsWith("do()", i) -> {
                    i += 4
                    enabled = true
                    continue
                }

                input.startsWith("don't()", i) -> {
                    i += 7
                    enabled = false
                    continue
                }

                else -> i++
            }
            val a = nextNumber()
            if (input[i] != ',') continue
            i++
            val b = nextNumber()
            if (input[i] != ')') continue
            i++
            val res = a * b
            ans += if (ignoreEnabled || enabled) res else 0
        }
        return ans
    }

    override fun part1(input: String): Int {
        return solve(input, true)
    }

    override fun part2(input: String): Int {
        return solve(input, false)
    }
}