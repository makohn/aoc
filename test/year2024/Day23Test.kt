package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    companion object {
        private val INPUT = """
            kh-tc
            qp-kh
            de-cg
            ka-co
            yn-aq
            qp-ub
            cg-tb
            vc-aq
            tb-ka
            wh-tc
            yn-cg
            kh-ub
            ta-co
            de-co
            tc-td
            tb-wq
            wh-td
            ta-ka
            td-qp
            aq-cg
            wq-ub
            ub-vc
            de-ta
            wq-aq
            wq-vc
            wh-yn
            ka-de
            kh-ta
            co-tc
            wh-qp
            tb-vc
            td-yn
        """.trimIndent()
    }

    private val day = Day23()

    @Test
    fun part1() {
        assertEquals(7, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals("co,de,ka,ta", day.part2(INPUT))
    }
}
