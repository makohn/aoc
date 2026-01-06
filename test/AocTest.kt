import kotlinx.serialization.json.Json
import model.Answers
import org.junit.jupiter.api.*
import util.LOCAL_DATA_DIR
import util.Solution
import util.readTestInput
import kotlin.io.path.Path
import kotlin.io.path.readText

abstract class AocTest(year: Int) {

    private val ans = Json.decodeFromString<Answers>(Path("$LOCAL_DATA_DIR/$year/answers.json").readText())

    abstract val days: List<Solution<*, *>>
    abstract val testDays: List<Solution<*, *>>

    private fun <T> assertEquals(expected: String, actual: T) {
        val expectedTypeMatched = when (actual) {
            is Long -> expected.toLong()
            is Int -> expected.toInt()
            else -> expected
        }
        Assertions.assertEquals(expectedTypeMatched, actual)
    }

    @TestFactory
    @DisplayName("Input")
    fun realInputs() = days.map {
        val answer = ans.answer[it.day]!!
        DynamicContainer.dynamicContainer(
            "Day%02d - %s".format(it.day, answer.name), listOf(
                DynamicTest.dynamicTest("Part01") { assertEquals(answer.part1, it.part1(it.input)) },
                DynamicTest.dynamicTest("Part02") { assertEquals(answer.part2, it.part2(it.input)) },
            )
        )
    }

    @TestFactory
    @DisplayName("Test")
    fun testInputs() = testDays.map { day ->
        val answer = ans.answer[day.day]!!
        DynamicContainer.dynamicContainer(
            "Day%02d - %s".format(day.day, answer.name), listOfNotNull(
                answer.test1?.let { DynamicTest.dynamicTest("Part01") { assertEquals(it, day.part1(day.testInput)) } },
                answer.test2?.let { DynamicTest.dynamicTest("Part02") { assertEquals(it, day.part2(readTestInput(day.year, day.day, "2"))) } }
            )
        )
    }
}