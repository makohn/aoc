package aoc.math

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class MatrixTest {

    private val matMulTestData = listOf(
        Triple(
            Matrix(
                2, 2,
                doubleArrayOf(
                    0.0, 1.0,
                    0.0, 0.0
                )
            ),
            Matrix(
                2, 2,
                doubleArrayOf(
                    0.0, 0.0,
                    1.0, 0.0
                )
            ),
            Matrix(
                2, 2,
                doubleArrayOf(
                    1.0, 0.0,
                    0.0, 0.0
                )
            )
        ),
        Triple(
            Matrix(
                3, 4,
                doubleArrayOf(
                    3.0, 1.5, 4.0, 3.0,
                    9.5, 2.0, 2.0, 0.5,
                    6.0, 7.0, 8.0, 12.0
                )
            ),
            Matrix(
                4, 3,
                doubleArrayOf(
                    12.0, 5.0, 6.5,
                    7.0, 0.0, 7.5,
                    8.0, 1.5, 2.0,
                    3.0, 7.0, 2.5
                )
            ),
            Matrix(
                3, 3,
                doubleArrayOf(
                    87.5, 42.0, 46.25,
                    145.5, 54.0, 82.0,
                    221.0, 126.0, 137.5
                )
            )
        )
    )

    private val inverseTestData = mapOf(
        Matrix4x4(
            doubleArrayOf(
                2.0, 1.0, 0.0, 0.0,
                0.0, 2.0, 1.0, 0.0,
                0.0, 0.0, 2.0, 1.0,
                0.0, 0.0, 0.0, 2.0
            )
        ) to Matrix4x4(
            doubleArrayOf(
                0.5, -0.25, 0.125, -0.0625,
                0.0, 0.5, -0.25, 0.125,
                -0.0, 0.0, 0.5, -0.25,
                0.0, -0.0, 0.0, 0.5
            )
        )
    )

    @TestFactory
    @DisplayName("A * B = C")
    fun testMatMul() = matMulTestData.map { (a, b, c) ->
        DynamicTest.dynamicTest("${a.m}x${a.n} * ${b.m}x${b.n}") {
            Assertions.assertEquals(c, a * b)
        }
    }

    @TestFactory
    @DisplayName("A.inverse()")
    fun testInverse() = inverseTestData.map { (a, b) ->
        DynamicTest.dynamicTest("${a.m}x${a.n}.inverse()") {
            Assertions.assertEquals(b, a.inverse())
        }
    }
}