package util.iter

import org.junit.jupiter.api.*

class IterTest {

    private val combineElementsTestData = mapOf(
        listOf("a", "b", "c", "d") to arrayOf(
            listOf(emptyList<String>()),
            listOf(
                listOf("a"),
                listOf("b"),
                listOf("c"),
                listOf("d")
            ),
            listOf(
                listOf("a", "b"),
                listOf("a", "c"),
                listOf("a", "d"),
                listOf("b", "c"),
                listOf("b", "d"),
                listOf("c", "d")
            ),
            listOf(
                listOf("a", "b", "c"),
                listOf("a", "b", "d"),
                listOf("a", "c", "d"),
                listOf("b", "c", "d")
            ),
            listOf(
                listOf("a", "b", "c", "d")
            )
        ),
        listOf(1, 2, 3, 4, 5) to arrayOf(
            listOf(emptyList<Int>()),
            listOf(
                listOf(1),
                listOf(2),
                listOf(3),
                listOf(4),
                listOf(5)
            ),
            listOf(
                listOf(1, 2),
                listOf(1, 3),
                listOf(1, 4),
                listOf(1, 5),
                listOf(2, 3),
                listOf(2, 4),
                listOf(2, 5),
                listOf(3, 4),
                listOf(3, 5),
                listOf(4, 5)
            ),
            listOf(
                listOf(1, 2, 3),
                listOf(1, 2, 4),
                listOf(1, 2, 5),
                listOf(1, 3, 4),
                listOf(1, 3, 5),
                listOf(1, 4, 5),
                listOf(2, 3, 4),
                listOf(2, 3, 5),
                listOf(2, 4, 5),
                listOf(3, 4, 5)
            ),
            listOf(
                listOf(1, 2, 3, 4),
                listOf(1, 2, 3, 5),
                listOf(1, 2, 4, 5),
                listOf(1, 3, 4, 5),
                listOf(2, 3, 4, 5)
            ),
            listOf(
                listOf(1, 2, 3, 4, 5)
            )
        )
    )

    private val transposeTestsData = mapOf(
        listOf(listOf("a", "b", "c"), listOf(1, 2, 3)) to listOf(listOf("a", 1), listOf("b", 2), listOf("c", 3))
    )

    private val bitsTestData = mapOf(
        7 to listOf(0, 1, 2),
        231 to listOf(0, 1, 2, 5, 6, 7),
        1024 to listOf(10),
        123123 to listOf(0, 1, 4, 5, 6, 7, 13, 14, 15, 16)
    )

    @TestFactory
    @DisplayName("List.combineElements")
    fun testCombineElements() = combineElementsTestData.map { (k, v) ->
        DynamicContainer.dynamicContainer(
            k.joinToString(),
            (0..k.size).map { i ->
                DynamicTest.dynamicTest("r = $i") {
                    Assertions.assertEquals(
                        v[i],
                        k.combineElements(i).toList()
                    )
                }
            }
        )
    }

    @TestFactory
    @DisplayName("List<List>.transpose")
    fun testTranspose() = transposeTestsData.map { (k, v) ->
        DynamicTest.dynamicTest(k.joinToString { it.joinToString("") }) {
            Assertions.assertEquals(v, k.transpose())
        }
    }

    @TestFactory
    @DisplayName("Int.bits")
    fun toBitset() = bitsTestData.map { (k, v) ->
        DynamicTest.dynamicTest(k.toString(2)) {
            Assertions.assertEquals(v, k.bits().asSequence().toList())
        }
    }
}