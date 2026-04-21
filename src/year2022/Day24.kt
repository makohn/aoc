package year2022

import util.core.*
import java.util.*
import kotlin.math.abs

class Day24 : Solution<Int, Int> {

    private data class Vec2(val x: Int, val y: Int) {
        operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
        operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)

        companion object {
            fun wrapTo(v: Vec2, bounds: Vec2) = Vec2((v.x + bounds.x) % bounds.x, (v.y + bounds.y) % bounds.y)
            fun abs(v: Vec2) = Vec2(abs(v.x), abs(v.y))
        }
    }

    private enum class Type {
        Down,
        Left,
        Up,
        Right,
    }

    private data class BlizzardUnit(val pos: Vec2, val type: Type)

    private data class Input(
        val start: Vec2,
        val target: Vec2,
        val blizzards: MutableList<Blizzards>,
        val bounds: Vec2,
    )

    private class Blizzards(
        var blizzards: MutableList<BlizzardUnit> = mutableListOf(),
        var positions: MutableSet<Vec2> = mutableSetOf(),
    )

    private val potentialMoves = arrayOf(
        Vec2(1, 0),
        Vec2(0, 1),
        Vec2(0, 0),
        Vec2(0, -1),
        Vec2(-1, 0),
    )

    private val horizontalMoveAxes = arrayOf(0, 1, 2, 3, 4)
    private val verticalMoveAxes = arrayOf(1, 0, 2, 4, 3)

    private fun walk(
        startStep: Int,
        start: Vec2,
        target: Vec2,
        blizzards: MutableList<Blizzards>,
        bounds: Vec2,
    ): Int {
        val q = PriorityQueue<Pair<Vec2, Int>>(compareBy { it.second })
        q.add(Pair(start, startStep))

        val tested = mutableSetOf<Pair<Vec2, Int>>()

        var best = Int.MAX_VALUE

        while (q.isNotEmpty()) {
            val (exp, curStep) = q.poll()
            if (curStep >= best) continue

            while (curStep >= blizzards.size) {
                val blizzardStep = Blizzards()
                for (b in blizzards.last().blizzards) {
                    val newPos = when (b.type) {
                        Type.Up -> Vec2.wrapTo(b.pos + Vec2(0, -1), bounds)
                        Type.Down -> Vec2.wrapTo(b.pos + Vec2(0, 1), bounds)
                        Type.Left -> Vec2.wrapTo(b.pos + Vec2(-1, 0), bounds)
                        Type.Right -> Vec2.wrapTo(b.pos + Vec2(1, 0), bounds)
                    }
                    blizzardStep.blizzards.add(BlizzardUnit(newPos, b.type))
                }
                blizzardStep.positions = blizzardStep.blizzards.map { it.pos }.toMutableSet()
                blizzards.add(blizzardStep)
            }

            val newBlizzards = blizzards[curStep]
            val delta = Vec2.abs(target - exp)
            val axes = if (delta.x > delta.y) horizontalMoveAxes else verticalMoveAxes

            for (i in axes) {
                val t = exp + potentialMoves[i]
                if (t == target) {
                    best = minOf(curStep, best)
                    break
                }
                if (t != exp && (t.x < 0 || t.y < 0 || t.x >= bounds.x || t.y >= bounds.y)) continue
                if (newBlizzards.positions.contains(t)) continue

                val tup = Pair(t, curStep + 1)
                if (tup !in tested) {
                    tested.add(tup)
                    q.add(tup)
                }
            }
        }
        return best
    }

    private fun parse(input: String): Input {
        val lines = input.lines()

        val height = lines.size - 2
        val width = lines[0].length - 2

        val blizzardStarts = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c ->
                when (c) {
                    '^' -> BlizzardUnit(Vec2(x - 1, y - 1), Type.Up)
                    'v' -> BlizzardUnit(Vec2(x - 1, y - 1), Type.Down)
                    '<' -> BlizzardUnit(Vec2(x - 1, y - 1), Type.Left)
                    '>' -> BlizzardUnit(Vec2(x - 1, y - 1), Type.Right)
                    else -> null
                }
            }
        }

        val blizzards = mutableListOf(
            Blizzards(
                blizzards = blizzardStarts.toMutableList(),
                positions = blizzardStarts.map { it.pos }.toMutableSet(),
            ),
        )

        val bounds = Vec2(width, height)
        val expStartPos = Vec2(0, -1)
        val target = Vec2(width - 1, height)
        return Input(expStartPos, target, blizzards, bounds)
    }

    override fun part1(input: String): Int {
        val (expStartPos, target, blizzards, bounds) = parse(input)
        return walk(1, expStartPos, target, blizzards, bounds)
    }

    override fun part2(input: String): Int {
        val (expStartPos, target, blizzards, bounds) = parse(input)
        var best = walk(1, expStartPos, target, blizzards, bounds)
        best = walk(best + 1, target, expStartPos, blizzards, bounds)
        return walk(best + 1, expStartPos, target, blizzards, bounds)
    }
}
