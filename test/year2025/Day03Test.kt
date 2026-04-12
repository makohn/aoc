package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        private val INPUT = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent()
    }

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(357, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(3121910778619, day.part2(INPUT))
    }
}
