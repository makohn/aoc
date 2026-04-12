package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {

    companion object {
        private val INPUT = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
        """.trimIndent()
    }

    private val day = Day20()

    @Test
    fun part1() {
        assertEquals(32000000, day.part1(INPUT))
    }
}
