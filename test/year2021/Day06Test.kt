package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        private const val INPUT = "3,4,3,1,2"
    }

    private val day = Day06(18)

    @Test
    fun part1() {
        assertEquals(26, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(26984457539, day.part2(INPUT))
    }
}
