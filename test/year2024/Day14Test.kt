package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    companion object {
        private val INPUT = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
        """.trimIndent()
    }

    private val day = Day14(11, 7)

    @Test
    fun part1() {
        assertEquals(12, day.part1(INPUT))
    }
}
