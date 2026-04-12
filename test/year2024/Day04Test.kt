package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    companion object {
        private val INPUT = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent()
    }

    private val day = Day04()

    @Test
    fun part1() {
        assertEquals(18, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(9, day.part2(INPUT))
    }
}
