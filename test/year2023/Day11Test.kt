package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    companion object {
        private val INPUT = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()
    }

    private val day = Day11(100)

    @Test
    fun part1() {
        assertEquals(374, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(8410, day.part2(INPUT))
    }
}
