package aoc

import org.junit.jupiter.api.*

class ListUtilsTest {

    private val tests = mapOf(
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

    @TestFactory
    @DisplayName("List.combineElements")
    fun testCombineElements() = tests.map { (k, v) ->
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
}