import year2023.Day01
import year2023.Day02
import year2023.Day03
import year2023.Day04
import year2023.Day05
import year2023.Day06
import year2023.Day07
import year2023.Day08
import year2023.Day09
import year2023.Day10
import year2023.Day11
import year2023.Day12
import year2023.Day13
import year2023.Day14
import year2023.Day15
import year2023.Day16
import year2023.Day17
import year2023.Day18
import year2023.Day19
import year2023.Day20
import year2023.Day21
import year2023.Day22
import year2023.Day23
import year2023.Day24
import year2023.Day25

class Test2023 : AocTest(year = 2023) {

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
        Day16(),
        Day17(),
        Day18(),
        Day19(),
        Day20(),
        Day21(),
        Day22(),
        Day23(),
        Day24(),
        Day25()
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
        Day11(100),
        Day12(),
        Day13(),
        Day14(),
        Day15(),
        Day16(),
        Day17(),
        Day18(),
        Day19(),
        Day20(),
        Day21(6, 5000), // TODO: ans2 = 16733044 doesn't seem to work
        Day22(),
        Day23(),
        Day24(7.0, 27.0),
        Day25()
    )
}