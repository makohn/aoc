package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    companion object {
        private val INPUT = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """.trimIndent()
    }

    private val day = Day10()

    @Test
    fun part1() {
        assertEquals(36, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(81, day.part2(INPUT))
    }
}
