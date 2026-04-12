package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {

    companion object {
        private val INPUT = """
                    ...#
                    .#..
                    #...
                    ....
            ...#.......#
            ........#...
            ..#....#....
            ..........#.
                    ...#....
                    .....#..
                    .#......
                    ......#.
            
            10R5L5R10L4R5L5
        """.trimIndent()
    }

    private val day = Day22()

    @Test
    fun part1() {
        assertEquals(6032, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(5031, day.part2(INPUT))
    }
}
