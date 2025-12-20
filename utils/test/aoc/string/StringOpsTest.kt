package aoc.string

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class StringOpsTest {

    private val extractIntsTestData = mapOf(
        "1, 2 sub, 5.923 7.8 -8.7 add 205.1234 10.99999" to listOf(1, 2, 5, 923, 7, 8, -8, 7, 205, 1234, 10, 99999),
        "pi=3.14159, e=2.71828" to listOf(3, 14159, 2, 71828),
        "5x5: -1 10 -2 11 3" to listOf(5, 5, -1, 10, -2, 11, 3),
        "[S] -2147483648 +2147483647 -> -0 +0 [E]" to listOf(-2147483648, 2147483647, 0, 0)
    )

    private val extractLongsTestData = mapOf(
        "1, 2 sub, 5.923 7.8 -8.7 add 205.1234 10.99999" to listOf<Long>(1, 2, 5, 923, 7, 8, -8, 7, 205, 1234, 10, 99999),
        "sens-A at t=2147483648 yields x=2147483701 y=-2147483650" to listOf(2147483648, 2147483701, -2147483650),
        "node-7 emits 45678901234567890 -> buffer(full)" to listOf(-7, 45678901234567890),
        "range-check(low=-9223372036854775807, high=+9223372036854775807)" to listOf(-9223372036854775807, 9223372036854775807)
    )

    private val extractDoublesTestData = mapOf(
        "1, 2 sub, 5.923 7.8 -8.7 add 205.1234 10.99999" to listOf(1.0, 2.0, 5.923, 7.8, -8.7, 205.1234, 10.99999),
        "pi=3.14159, e=2.71828" to listOf(3.14159, 2.71828),
        "8. 9.-1023" to listOf(8.0, 9.0, -1023.0),
        "344051711837792, 354119482543737 @ -90, 10" to listOf(344051711837792.0, 354119482543737.0, -90.0, 10.0)
    )

    @TestFactory
    @DisplayName("String.extractInts")
    fun testExtractInts() = extractIntsTestData.map { (k, v) ->
        DynamicTest.dynamicTest(k) {
            Assertions.assertEquals(v, k.extractInts())
        }
    }

    @TestFactory
    @DisplayName("String.extractLongs")
    fun testExtractLongs() = extractLongsTestData.map { (k, v) ->
        DynamicTest.dynamicTest(k) {
            Assertions.assertEquals(v, k.extractLongs())
        }
    }

    @TestFactory
    @DisplayName("String.extractDoubles")
    fun extractDoubles() = extractDoublesTestData.map { (k, v) ->
        DynamicTest.dynamicTest(k) {
            Assertions.assertEquals(v, k.extractDoubles())
        }
    }
}