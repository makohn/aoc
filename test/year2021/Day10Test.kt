package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    companion object {
        private val INPUT = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent()
    }

    private val day = Day10()

    @Test
    fun part1() {
        assertEquals(26397, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(288957, day.part2(INPUT))
    }
}
