package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    companion object {
        private val INPUT = """
            1=-0-2
            12111
            2=0=
            21
            2=01
            111
            20012
            112
            1=-1=
            1-12
            12
            1=
            122
        """.trimIndent()
    }

    private val day = Day25()

    @Test
    fun part1() {
        assertEquals("2=-1=0", day.part1(INPUT))
    }
}
