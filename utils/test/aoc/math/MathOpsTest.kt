package aoc.math

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class MathOpsTest {

    private val gcdTestData = mapOf(
        (8L to 12L) to 4L,
        (252L to 105L) to 21L,
        (24140L to 16762L) to 34L,
        (16762L to 24140L) to 34L,
        (3141592653589793238 to 2718281828459045235) to 3L
    )

    private val lcmTestData = mapOf(
        (4L to 5L) to 20L,
        (36L to 48L) to 144L,
        (3123L to 8575L) to 26779725L,
        (8575L to 3123L) to 26779725L,
        (3123456789 to 8573748456) to 8926577607357155928
    )

    @TestFactory
    @DisplayName("gcd(a, b)")
    fun testGcd() = gcdTestData.map { (k, v) ->
        DynamicTest.dynamicTest("gcd(${k.first}, ${k.second}) = $v") {
            Assertions.assertEquals(v, gcd(k.first, k.second))
        }
    }

    @TestFactory
    @DisplayName("lcm(a, b)")
    fun testLcm() = lcmTestData.map { (k, v) ->
        DynamicTest.dynamicTest("lcm(${k.first}, ${k.second}) = $v") {
            Assertions.assertEquals(v, lcm(k.first, k.second))
        }
    }
}