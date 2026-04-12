package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    companion object {
        private val INPUT = """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
        """.trimIndent()
    }

    private val day = Day16()

    @Test
    fun part1() {
        assertEquals(7036, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(45, day.part2(INPUT))
    }
}
