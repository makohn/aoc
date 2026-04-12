package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    companion object {
        private val INPUT = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()
    }

    private val day = Day13()

    @Test
    fun part1() {
        assertEquals(405, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(400, day.part2(INPUT))
    }
}
