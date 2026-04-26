package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    companion object {
        private val INPUT = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()
    }

    private val day = Day10()

    @Test
    fun part1() {
        assertEquals(22 * 10, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(19208, day.part2(INPUT))
    }
}
