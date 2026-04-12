package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    companion object {
        private const val INPUT = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
    }

    private val day = Day15()

    @Test
    fun part1() {
        assertEquals(1320, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(145, day.part2(INPUT))
    }
}
