package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    companion object {
        private val INPUT = """
            ....#..
            ..###.#
            #...#.#
            .#...##
            #.###..
            ##.#.##
            .#..#..
        """.trimIndent()
    }

    private val day = Day23()

    @Test
    fun part1() {
        assertEquals(110, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(20, day.part2(INPUT))
    }
}
