package year2025

import util.Solution

class Day04 : Solution<Int, Int>(year = 2025, day = 4) {

    override fun part1(input: String): Int {
        val inputLines = input.lines()
        val grid = Array(inputLines.size) { idx -> inputLines[idx].toCharArray() }
        val (n, m) = grid.size to grid[0].size
        var sum = 0
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (grid[i][j] == '@') {
                    var count = 0
                    for (a in -1..1) for (b in -1..1) {
                        val ai = i + a
                        val bj = j + b
                        if (ai in 0..<n && bj in 0..<m && !(ai == i && bj == j) && grid[ai][bj] == '@') {
                            count++
                        }
                    }
                    if (count < 4) sum++
                }
            }
        }
        return sum
    }

    override fun part2(input: String): Int {
        val inputLines = input.lines()
        val grid = Array(inputLines.size) { idx -> inputLines[idx].toCharArray() }
        val (n, m) = grid.size to grid[0].size
        var prevSum = 0
        var sum = 0
        while (true) {
            for (i in 0..<n) {
                for (j in 0..<m) {
                    if (grid[i][j] == '@') {
                        var count = 0
                        for (a in -1..1) for (b in -1..1) {
                            val ai = i + a
                            val bj = j + b
                            if (ai in 0..<n && bj in 0..<m && !(ai == i && bj == j) && grid[ai][bj] == '@') {
                                count++
                            }
                        }
                        if (count < 4) {
                            grid[i][j] = 'X'
                            sum++
                        }
                    }
                }
            }
            if (prevSum == sum) break
            prevSum = sum
        }

        return sum
    }
}

fun main() = Day04().run {
    println(part1(input))
    println(part2(input))
}