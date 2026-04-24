package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    companion object {
        private val INPUT = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
    }

    private val day = Day08()

    @Test
    fun part1() {
        assertEquals(5, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(8, day.part2(INPUT))
    }
}
