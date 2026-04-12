package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {

    companion object {
        private val INPUT = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """.trimIndent()
    }

    private val day = Day20(2, 50)

    @Test
    fun part1() {
        assertEquals(44, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(285, day.part2(INPUT))
    }
}
