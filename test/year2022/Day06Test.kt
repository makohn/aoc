package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        private const val INPUT = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    }

    private val day = Day06()

    @Test
    fun part1() {
        assertEquals(7, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(19, day.part2(INPUT))
    }
}
