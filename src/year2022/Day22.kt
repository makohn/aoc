package year2022

import util.core.*
import util.math.*
import util.parse.*
import util.point.*

class Day22 : Solution<Int, Int> {

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
        val block: Int,
    ) {
        fun tile(point: Point): Char = if (point.x in 0..<width && point.y in 0..<height) {
            tiles[point.y * width + point.x]
        } else {
            ' '
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

    data class Vec3(
        val x: Int,
        val y: Int,
        val z: Int,
    ) {
        operator fun unaryMinus() = Vec3(-this.x, -this.y, -this.z)
    }

    data class Face(
        val corner: Point,
        val a: Vec3,
        val b: Vec3,
        val c: Vec3,
    )

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
        var pos = Point(board.start, 0)
        var dir = RIGHT
        for (move in moves) {
            when (move) {
                is Move.Left -> dir = dir.counterClockwise()
                is Move.Right -> dir = dir.clockwise()
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
                                } else {
                                    return@repeat
                                }
                            }
                        }
                    }
                }
            }
        }
        val posScore = 1000 * (pos.y + 1) + 4 * (pos.x + 1)
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
        val (boardStr, movesStr) = input.split("\n\n")
        val board = Board(boardStr)
        val moves = parseMoves(movesStr)
        val block = board.block
        val start = Face(
            corner = Point(board.start - board.start % block, 0),
            a = Vec3(1, 0, 0),
            b = Vec3(0, 1, 0),
            c = Vec3(0, 0, 1),
        )
        val todo = arrayListOf(start)
        val faces = hashMapOf(start.c to start)
        val corners = hashMapOf(start.corner to start)

        while (todo.isNotEmpty()) {
            val (corner, a, b, c) = todo.removeFirst()
            val neighbors = arrayOf(
                Face(corner = corner + Point(-block, 0), a = -c, b = b, c = a),
                Face(corner = corner + Point(block, 0), a = c, b = b, c = -a),
                Face(corner = corner + Point(0, -block), a = a, b = -c, c = b),
                Face(corner = corner + Point(0, block), a = a, b = c, c = -b),
            )
            for (neighbor in neighbors) {
                if (board.tile(neighbor.corner) != ' ' && neighbor.c !in faces) {
                    todo.add(neighbor)
                    faces[neighbor.c] = neighbor
                    corners[neighbor.corner] = neighbor
                }
            }
        }
        return password(board, moves) { pos, dir ->
            val edge = block - 1
            val offset = Point(pos.x % block, pos.y % block)
            val corner = pos - offset
            val (_, a, b, c) = corners[corner]!!
            val nextC = when (dir) {
                LEFT -> a
                RIGHT -> -a
                UP -> b
                DOWN -> -b
                else -> error(dir)
            }
            val (nextCorner, nextA, nextB, _) = faces[nextC]!!
            val nextDir = when (c) {
                nextA -> RIGHT
                -nextA -> LEFT
                nextB -> DOWN
                -nextB -> UP
                else -> error(c)
            }
            val nextOffset = when (dir) {
                LEFT -> when (nextDir) {
                    LEFT -> Point(edge, offset.y)
                    RIGHT -> Point(0, edge - offset.y)
                    DOWN -> Point(offset.y, 0)
                    UP -> Point(edge - offset.y, edge)
                    else -> error(nextDir)
                }
                RIGHT -> when (nextDir) {
                    LEFT -> Point(edge, edge - offset.y)
                    RIGHT -> Point(0, offset.y)
                    DOWN -> Point(edge - offset.y, 0)
                    UP -> Point(offset.y, edge)
                    else -> error(nextDir)
                }
                DOWN -> when (nextDir) {
                    LEFT -> Point(edge, offset.x)
                    RIGHT -> Point(0, edge - offset.x)
                    DOWN -> Point(offset.x, 0)
                    UP -> Point(edge - offset.x, edge)
                    else -> error(nextDir)
                }
                UP -> when (nextDir) {
                    LEFT -> Point(edge, edge - offset.x)
                    RIGHT -> Point(0, offset.x)
                    DOWN -> Point(edge - offset.x, 0)
                    UP -> Point(offset.x, edge)
                    else -> error(nextDir)
                }
                else -> error(dir)
            }
            val nextPos = nextCorner + nextOffset
            nextPos to nextDir
        }
    }
}
