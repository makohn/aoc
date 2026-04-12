package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    companion object {
        private const val INPUT = "target area: x=20..30, y=-10..-5"
    }

    private val day = Day17()

    @Test
    fun part1() {
        assertEquals(45, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(112, day.part2(INPUT))
    }
}
