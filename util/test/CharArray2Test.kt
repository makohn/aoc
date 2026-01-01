import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class CharArray2Test {

    private val rotatedTestData = mapOf(
        arrayOf(
            charArrayOf('A', 'B', 'C'),
            charArrayOf('D', 'E', 'F'),
            charArrayOf('G', 'H', 'I')
        ) to arrayOf(
            charArrayOf('G', 'D', 'A'),
            charArrayOf('H', 'E', 'B'),
            charArrayOf('I', 'F', 'C')
        )
    )

    @TestFactory
    @DisplayName("CharArray2.rotated")
    fun testRotated() = rotatedTestData.map { (k, v) ->
        DynamicTest.dynamicTest(k.rowsToString()) {
            Assertions.assertArrayEquals(v, k.rotated())
        }
    }
}