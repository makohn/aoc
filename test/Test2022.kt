import year2022.Day01
import year2022.Day02
import year2022.Day03
import year2022.Day04
import year2022.Day05
import year2022.Day06
import year2022.Day07
import year2022.Day08
import year2022.Day09
import year2022.Day10
import year2022.Day11
import year2022.Day12
import year2022.Day13
import year2022.Day14
import year2022.Day15
import year2022.Day16

class Test2022 : AocTest(year = 2022) {

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
        Day15(),
        Day16()
    )

    override val testDays = listOf(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
//        Day05(), // TODO: Broken
        Day06(),
        Day07(),
        Day08(),
        Day09(),
        Day10(),
        Day11(),
        Day12(),
        Day13(),
        Day14(),
        Day15(10, 0..20),
        Day16()
    )
}