import year2021.Day01
import year2021.Day02
import year2021.Day03
import year2021.Day04
import year2021.Day05
import year2021.Day06
import year2021.Day07
import year2021.Day08
import year2021.Day09
import year2021.Day10
import year2021.Day11
import year2021.Day12
import year2021.Day13
import year2021.Day14
import year2021.Day15
import year2021.Day16
import year2021.Day17
import year2021.Day18
import year2021.Day19
import year2021.Day20
import year2021.Day21
import year2021.Day22
import year2021.Day23
import year2021.Day24
import year2021.Day25

class Test2021 : AocTest(year = 2021) {

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
//        Solution(Day22(), 543306, 1285501151402480), // TODO: Part 2 runs very slow (15min, 43sec, 196 ms)
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
        Day06(18),
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
        Day25(),
    )
}