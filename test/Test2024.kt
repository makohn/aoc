import year2024.Day01
import year2024.Day02
import year2024.Day03
import year2024.Day04
import year2024.Day05
import year2024.Day06
import year2024.Day07
import year2024.Day08
import year2024.Day09
import year2024.Day10
import year2024.Day11
import year2024.Day12
import year2024.Day13
import year2024.Day14
import year2024.Day15

class Test2024 : AocTest(year = 2024) {

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
        Day12(),
        Day13(),
        Day14(),
        Day15()
    )

    override val testDays = listOf(
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
        Day12(),
        Day13(),
        Day14(11, 7),
        Day15()
    )
}