package year2021

import util.core.*
import util.iter.transpose

class Day04 : Solution<Int, Int>(year = 2021, day = 4) {

    companion object {
        const val NUM_ROWS = 5
        const val BOARD_SIZE = NUM_ROWS * NUM_ROWS
        const val MARKER = -1
        const val INVALID_INDEX = -1
    }

    override fun part1(input: String): Int {
        val (numbers, games) = parseInput(input)
        val (number, index) = evaluate(numbers, games)
        return games.chunked(BOARD_SIZE)[index].filter { it > MARKER }.sum() * number
    }

    override fun part2(input: String): Int {
        val (numbers, games) = parseInput(input)
        val (number, index) = evaluate(numbers, games) { false }
        return games.chunked(BOARD_SIZE)[index].filter { it > MARKER }.sum() * number
    }

    private fun evaluate(numbers: List<Int>, games: MutableList<Int>, terminate: (Set<Int>) -> Boolean = Set<Int>::isNotEmpty): Pair<Int, Int> {
        var winningIndex = INVALID_INDEX
        var winningNumber = INVALID_INDEX
        val indices = mutableSetOf<Int>()
        numbers
            .dropWhile { number ->
                games.replaceAll { nr -> if (nr == number) MARKER else nr }
                val index = games
                    .asSequence()
                    .chunked(BOARD_SIZE)
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
    private fun bingoRow(game: List<Int>) = game.chunked(NUM_ROWS).map { it.sum() }.contains(NUM_ROWS * MARKER)
    private fun bingoCol(game: List<Int>) = bingoRow(game.chunked(NUM_ROWS).transpose().flatten())
}
