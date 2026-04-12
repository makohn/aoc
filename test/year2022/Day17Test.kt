package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    companion object {
        private const val INPUT = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
    }

    private val day = Day17()

    @Test
    fun part1() {
        assertEquals(3068, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(1514285714288, day.part2(INPUT))
    }
}
