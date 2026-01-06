package year2021

import util.core.*
import util.iter.transpose

class Day04 : Solution<Int, Int>(year = 2021, day = 4) {

    companion object {
        const val numRows = 5
        const val boardSize = numRows * numRows
        const val marker = -1
        const val invalidIndex = -1
    }

    override fun part1(input: String): Int {
        val (numbers, games) = parseInput(input)
        val (number, index) = evaluate(numbers, games)
        return games.chunked(boardSize)[index].filter { it > marker }.sum() * number
    }

    override fun part2(input: String): Int {
        val (numbers, games) = parseInput(input)
        val (number, index) = evaluate(numbers, games) { false }
        return games.chunked(boardSize)[index].filter { it > marker }.sum() * number
    }

    private fun evaluate(numbers: List<Int>, games: MutableList<Int>, terminate: (Set<Int>) -> Boolean = Set<Int>::isNotEmpty)
            : Pair<Int, Int> {
        var winningIndex = invalidIndex
        var winningNumber = invalidIndex
        val indices = mutableSetOf<Int>()
        numbers
            .dropWhile { number ->
                games.replaceAll { nr -> if (nr == number) marker else nr }
                val index = games
                    .asSequence()
                    .chunked(boardSize)
                    .withIndex()
                    .filter(::isBingo)
                    .map { it.index }
                    .filter { !indices.contains(it) }
                    .toList()
                if (index.isNotEmpty()) {
                    winningIndex = index.last()
                    winningNumber = number
                    indices += index
                }
                !terminate(indices) && indices.size < games.size / 25
            }
        return winningNumber to winningIndex
    }

    private fun parseInput(input: String): Pair<List<Int>, MutableList<Int>> {
        val inputLines = input.lines()
        val numbers = inputLines.first().split(",").map(String::toInt)
        val games = inputLines
            .asSequence()
            .drop(1)
            .filter { it.isNotEmpty() }
            .map { it.trim().split("\\s+".toRegex()).map(String::toInt) }
            .flatten()
            .toMutableList()
        return numbers to games
    }

    private fun isBingo(game: IndexedValue<List<Int>>) = bingoRow(game.value) or bingoCol(game.value)
    private fun bingoRow(game: List<Int>) = game.chunked(numRows).map { it.sum() }.contains(numRows * marker)
    private fun bingoCol(game: List<Int>) = bingoRow(game.chunked(numRows).transpose().flatten())
}

fun main() = Day04().run {
    println(part1(input))
    println(part2(input))
}