package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object {
        private val INPUT1 = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()

        private val INPUT2 = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()
    }

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(142, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(281, day.part2(INPUT2))
    }
}
