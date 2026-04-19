package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        private val INPUT = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
    }

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(7, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(336, day.part2(INPUT))
    }
}
