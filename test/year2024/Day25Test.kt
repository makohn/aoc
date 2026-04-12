package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    companion object {
        private val INPUT = """
            #####
            .####
            .####
            .####
            .#.#.
            .#...
            .....

            #####
            ##.##
            .#.##
            ...##
            ...#.
            ...#.
            .....

            .....
            #....
            #....
            #...#
            #.#.#
            #.###
            #####

            .....
            .....
            #.#..
            ###..
            ###.#
            ###.#
            #####

            .....
            .....
            .....
            #....
            #.#..
            #.#.#
            #####
        """.trimIndent()
    }

    private val day = Day25()

    @Test
    fun part1() {
        assertEquals(3, day.part1(INPUT))
    }
}
