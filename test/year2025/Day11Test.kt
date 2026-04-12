package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    companion object {
        private val INPUT = """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out
        """.trimIndent()
    }

    private val day = Day11()

    @Test
    fun part1() {
        assertEquals(5, day.part1(INPUT))
    }
}
