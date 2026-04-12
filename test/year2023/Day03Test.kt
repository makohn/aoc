package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        private val INPUT = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()
    }

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(4361, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(467835, day.part2(INPUT))
    }
}
