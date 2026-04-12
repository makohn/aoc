package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    companion object {
        private val INPUT = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent()
    }

    private val day = Day14()

    @Test
    fun part1() {
        assertEquals(136, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(64, day.part2(INPUT))
    }
}
