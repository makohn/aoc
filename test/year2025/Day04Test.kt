package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    companion object {
        private val INPUT = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
        """.trimIndent()
    }

    private val day = Day04()

    @Test
    fun part1() {
        assertEquals(13, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(43, day.part2(INPUT))
    }
}
