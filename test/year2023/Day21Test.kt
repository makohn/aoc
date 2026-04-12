package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    companion object {
        private val INPUT = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent()
    }

    private val day = Day21(6, 5000)

    @Test
    fun part1() {
        assertEquals(16, day.part1(INPUT))
    }
}
