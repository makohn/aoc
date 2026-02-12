package year2024

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State
import year2023.Day15

@Suppress("Unused")
@State(Scope.Benchmark)
open class Benchmark2024 {

    private val day01 = Day01()
    private val day02 = Day02()
    private val day03 = Day03()
    private val day04 = Day04()
    private val day05 = Day05()
    private val day06 = Day06()
    private val day07 = Day07()
    private val day08 = Day08()
    private val day09 = Day09()
    private val day10 = Day10()
    private val day11 = Day11()
    private val day12 = Day12()
    private val day13 = Day13()
    private val day14 = Day14()
    private val day15 = Day15()
    private val day16 = Day16()

    // @formatter:off
    @Benchmark open fun day01Part1() = day01.part1(day01.input)
    @Benchmark open fun day01Part2() = day01.part2(day01.input)
    @Benchmark open fun day02Part1() = day02.part1(day02.input)
    @Benchmark open fun day02Part2() = day02.part2(day02.input)
    @Benchmark open fun day03Part1() = day03.part1(day03.input)
    @Benchmark open fun day03Part2() = day03.part2(day03.input)
    @Benchmark open fun day04Part1() = day04.part1(day04.input)
    @Benchmark open fun day04Part2() = day04.part2(day04.input)
    @Benchmark open fun day05Part1() = day05.part1(day05.input)
    @Benchmark open fun day05Part2() = day05.part2(day05.input)
    @Benchmark open fun day06Part1() = day06.part1(day06.input)
    @Benchmark open fun day06Part2() = day06.part2(day06.input)
    @Benchmark open fun day07Part1() = day07.part1(day07.input)
    @Benchmark open fun day07Part2() = day07.part2(day07.input)
    @Benchmark open fun day08Part1() = day08.part1(day08.input)
    @Benchmark open fun day08Part2() = day08.part2(day08.input)
    @Benchmark open fun day09Part1() = day09.part1(day09.input)
    @Benchmark open fun day09Part2() = day09.part2(day09.input)
    @Benchmark open fun day10Part1() = day10.part1(day10.input)
    @Benchmark open fun day10Part2() = day10.part2(day10.input)
    @Benchmark open fun day11Part1() = day11.part1(day11.input)
    @Benchmark open fun day11Part2() = day11.part2(day11.input)
    @Benchmark open fun day12Part1() = day12.part1(day12.input)
    @Benchmark open fun day12Part2() = day12.part2(day12.input)
    @Benchmark open fun day13Part1() = day13.part1(day13.input)
    @Benchmark open fun day13Part2() = day13.part2(day13.input)
    @Benchmark open fun day14Part1() = day14.part1(day14.input)
    @Benchmark open fun day14Part2() = day14.part2(day14.input)
    @Benchmark open fun day15Part1() = day15.part1(day15.input)
    // TODO: day15Part2
    @Benchmark open fun day16Part1() = day16.part1(day16.input)
    // @formatter:on
}
