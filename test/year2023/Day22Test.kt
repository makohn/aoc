package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {

    companion object {
        private val INPUT = """
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9
        """.trimIndent()
    }

    private val day = Day22()

    @Test
    fun part1() {
        assertEquals(5, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(7, day.part2(INPUT))
    }
}
