package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    companion object {
        private val INPUT = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent()
    }

    private val day = Day08()

    @Test
    fun part1() {
        assertEquals(21, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(8, day.part2(INPUT))
    }
}
