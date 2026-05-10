package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    companion object {
        private const val INPUT = "3,1,2"
    }

    private val day = Day15()

    @Test
    fun part1() {
        assertEquals(1836, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(362, day.part2(INPUT))
    }
}
