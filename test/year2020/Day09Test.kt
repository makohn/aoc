package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    companion object {
        private val INPUT = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()
    }

    private val day = Day09(5)

    @Test
    fun part1() {
        assertEquals(127, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(62, day.part2(INPUT))
    }
}
