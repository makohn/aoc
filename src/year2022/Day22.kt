package year2022

import util.core.*
import util.math.*
import util.parse.*
import util.point.*

class Day22 : Solution<Int, Int>(year = 2022, day = 22) {

    private sealed interface Move {
        data object Left : Move
        data object Right : Move
        data class Forward(val n: Int) : Move
    }

    private class Board(
        val width: Int,
        val height: Int,
        val tiles: CharArray,
        val start: Int,
        val block: Int
    ) {
        fun tile(point: Point): Char {
            return if (point.j in 0..<width && point.i in 0..<height) {
                tiles[point.i * width + point.j]
            } else ' '
        }
    }

    private fun Board(str: String): Board {
        val lines = str.lines()
        val width = lines.maxOf { it.length }
        val height = lines.size
        val tiles = CharArray(width * height) { ' ' }

        for ((y, row) in lines.withIndex()) {
            for ((x, col) in row.withIndex()) {
                tiles[y * width + x] = col
            }
        }

        val start = tiles.indexOfFirst { it == '.' }
        val block = gcd(width, height)
        return Board(width, height, tiles, start, block)
    }

    private fun parseMoves(str: String): List<Move> {
        val letters = str.filter { it.isLetter() }.iterator()
        val moves = ArrayList<Move>()
        for (n in str.extractInts()) {
            moves.add(Move.Forward(n))
            if (letters.hasNext()) {
                val direction = letters.next()
                moves.add(if (direction == 'L') Move.Left else Move.Right)
            }
        }
        return moves
    }

    private fun password(board: Board, moves: List<Move>, handleNone: (Point, Point) -> Pair<Point, Point>): Int {
        var pos = Point(0, board.start)
        var dir = RIGHT
        for (move in moves) {
            when (move) {
                is Move.Left -> dir = dir.clockwise()
                is Move.Right -> dir = dir.counterClockwise()
                is Move.Forward -> {
                    repeat(move.n) {
                        val next = pos + dir
                        when (board.tile(next)) {
                            '#' -> return@repeat
                            '.' -> pos = next
                            ' ' -> {
                                val (nextPos, nextDir) = handleNone(pos, dir)
                                if (board.tile(nextPos) == '.') {
                                    pos = nextPos
                                    dir = nextDir
                                } else return@repeat
                            }
                        }
                    }
                }
            }
        }
        val posScore = 1000 * (pos.i + 1) + 4 * (pos.j + 1)
        val dirScore = when (dir) {
            RIGHT -> 0
            DOWN -> 1
            LEFT -> 2
            UP -> 3
            else -> error(dir)
        }
        return posScore + dirScore
    }

    override fun part1(input: String): Int {
        val (boardStr, movesStr) = input.split("\n\n")
        val board = Board(boardStr)
        val moves = parseMoves(movesStr)
        val block = board.block
        return password(board, moves) { pos, dir ->
            val reverse = dir * -block
            val next = pos + reverse
            while (board.tile(next) != ' ') {
                next += reverse
            }
            next += dir
            next to dir
        }
    }

    override fun part2(input: String): Int {
        return 0
    }
}