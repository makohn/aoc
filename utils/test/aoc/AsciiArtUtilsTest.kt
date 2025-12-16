package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class AsciiArtUtilsTest {

    private val tests = mapOf(
        """
        .##..###...##..
        #..#.#..#.#..#.
        #..#.###..#....
        ####.#..#.#....
        #..#.#..#.#..#.
        #..#.###...##..
        """.trimIndent() to "ABC"
    )

    @TestFactory
    fun testConversion() = tests.map { (k, v) ->
        DynamicTest.dynamicTest(v) { Assertions.assertEquals(v, k.fromAsciiArt()) }
    }
}