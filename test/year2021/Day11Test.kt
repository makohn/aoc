package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    companion object {
        private val INPUT = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent()
    }

    private val day = Day11()

    @Test
    fun part1() {
        assertEquals(1656, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(195, day.part2(INPUT))
    }
}
