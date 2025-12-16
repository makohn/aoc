package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class StringUtilsTest {

    private val tests = mapOf(
        "5x5: -1 10 -2 11 3" to listOf(5, 5, -1, 10, -2, 11, 3),
        "[S] -2147483648 +2147483647 -> -0 +0 [E]" to listOf(-2147483648, 2147483647, 0, 0)
    )

    @TestFactory
    @DisplayName("String.extractInts")
    fun testExtractInts() = tests.map { (k, v) ->
        DynamicTest.dynamicTest(k) {
            Assertions.assertEquals(v, k.extractInts())
        }
    }
}