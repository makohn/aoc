package year2023

import util.Solution
import util.grid.CharGrid
import util.grid.shape
import util.grid.toCharGrid

class Day16 : Solution<Int, Int>(year = 2023, day = 16) {

    private data class Pos(val i: Int, val j: Int, val d: Dir)

    private enum class Dir {
        Up,
        Right,
        Down,
        Left;

        fun apply(p: Pos) = when(this) {
            Up -> Pos(p.i-1, p.j, this)
            Right -> Pos(p.i, p.j+1, this)
            Left -> Pos(p.i, p.j-1, this)
            Down -> Pos(p.i+1, p.j, this)
        }

        // /
        // ^ -> >  U  R
        // > -> ^  R  U
        // v -> <  D  L
        // < -> v  L  D
        fun mirror90() = when(this) {
            Up -> Right
            Right -> Up
            Down -> Left
            Left -> Down
        }

        // \
        // ^ -> <  U  L
        // > -> v  R  D
        // v -> >  D  R
        // < -> ^  L  U
        fun mirrorMinus90() = when(this) {
            Up -> Left
            Right -> Down
            Down -> Right
            Left -> Up
        }
    }

    private fun findEnergizedTiles(map: CharGrid, startPos: Pos): Int {
        val (n, m) = map.shape
        val seen = HashSet<Pos>()
        val nextSteps = ArrayDeque<Pos>()
        nextSteps.addLast(startPos)
        while (nextSteps.isNotEmpty()) {
            val pos = nextSteps.removeFirst()
            val (i, j, d) = pos
            if (pos in seen || i !in 0..<n || j !in 0..<m) {
                continue
            }
            seen += pos
            val c = map[i][j]
            when (c) {
                '.' -> nextSteps.add(d.apply(pos))
                '|' -> {
                    if (d in listOf(Dir.Left, Dir.Right)) {
                        nextSteps += Dir.Up.apply(pos)
                        nextSteps += Dir.Down.apply(pos)
                    } else {
                        nextSteps.add(d.apply(pos))
                    }
                }
                '-' -> {
                    if (d in listOf(Dir.Up, Dir.Down)) {
                        nextSteps += Dir.Left.apply(pos)
                        nextSteps += Dir.Right.apply(pos)
                    } else {
                        nextSteps.add(d.apply(pos))
                    }
                }
                '\\' -> {
                    val newDir = d.mirrorMinus90()
                    nextSteps += newDir.apply(pos)
                }
                '/' -> {
                    val newDir = d.mirror90()
                    nextSteps += newDir.apply(pos)
                }
                else -> Unit
            }
        }

        return seen
            .map { it.i to it.j }
            .toSet()
            .count()
    }

    override fun part1(input: String): Int {
        val map = input.lines().toCharGrid()
        return findEnergizedTiles(map, Pos(0, 0, Dir.Right))
    }

    override fun part2(input: String): Int {
        val map = input.lines().toCharGrid()
        val (n, m) = map.shape
        val startPositions = mutableListOf<Pos>()
        for (y in 0..<m) {
            startPositions.add(Pos(0, y, Dir.Down))
            startPositions.add(Pos(n-1, y, Dir.Up))
        }
        for (x in 0..<n) {
            startPositions.add(Pos(x, 0, Dir.Right))
            startPositions.add(Pos(x, m-1, Dir.Left))
        }

        return startPositions.maxOf { findEnergizedTiles(map, it) }
    }
}

fun main() = Day16().run {
    println(part1(input))
    println(part2(input))
}