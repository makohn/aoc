package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        private const val INPUT1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        private const val INPUT2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    }

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(161, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(48, day.part2(INPUT2))
    }
}
