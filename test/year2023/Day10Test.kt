package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    companion object {
        private val INPUT1 = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent()

        private val INPUT2 = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
        """.trimIndent()
    }

    private val day = Day10()

    @Test
    fun part1() {
        assertEquals(4, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(4, day.part2(INPUT2))
    }
}
