package util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class AsciiArtUtilsTest {

    private val testData = mapOf(
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
    fun testConversion() = testData.map { (k, v) ->
        DynamicTest.dynamicTest(v) { Assertions.assertEquals(v, k.fromAsciiArt()) }
    }
}