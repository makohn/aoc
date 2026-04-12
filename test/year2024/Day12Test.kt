package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    companion object {
        private val INPUT = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """.trimIndent()
    }

    private val day = Day12()

    @Test
    fun part1() {
        assertEquals(1930, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(1206, day.part2(INPUT))
    }
}
