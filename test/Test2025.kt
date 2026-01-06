import year2025.Day01
import year2025.Day02
import year2025.Day03
import year2025.Day04
import year2025.Day05
import year2025.Day06
import year2025.Day07
import year2025.Day08
import year2025.Day09
import year2025.Day10
import year2025.Day11
import year2025.Day12

class Test2025 : AocTest(year = 2025) {

    override val days = listOf(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
        Day05(),
        Day06(),
        Day07(),
        Day08(),
        Day09(),
        Day10(),
        Day11(),
        Day12()
    )

    override val testDays = listOf(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
        Day05(),
        Day06(),
        Day07(),
        Day08(10),
        Day09(),
        Day10(),
        Day11()
    )
}