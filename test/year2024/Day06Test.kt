package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        private val INPUT = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """.trimIndent()
    }

    private val day = Day06()

    @Test
    fun part1() {
        assertEquals(41, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(6, day.part2(INPUT))
    }
}
