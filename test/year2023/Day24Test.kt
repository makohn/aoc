package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {

    companion object {
        private val INPUT = """
            19, 13, 30 @ -2,  1, -2
            18, 19, 22 @ -1, -1, -2
            20, 25, 34 @ -2, -2, -4
            12, 31, 28 @ -1, -2, -1
            20, 19, 15 @  1, -5, -3
        """.trimIndent()
    }

    private val day = Day24(7.0, 27.0)

    @Test
    fun part1() {
        assertEquals(2, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(47, day.part2(INPUT))
    }
}
