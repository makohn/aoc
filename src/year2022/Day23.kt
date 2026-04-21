package year2022

import util.core.*

class Day23 : Solution<Int, Int> {

    private data class Vec2(val x: Int, val y: Int) {
        operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
    }

    private enum class Dir(val checks: Array<Vec2>, val move: Vec2) {
        N(arrayOf(Vec2(-1, -1), Vec2(0, -1), Vec2(1, -1)), Vec2(0, -1)),
        S(arrayOf(Vec2(-1, 1), Vec2(0, 1), Vec2(1, 1)), Vec2(0, 1)),
        W(arrayOf(Vec2(-1, -1), Vec2(-1, 0), Vec2(-1, 1)), Vec2(-1, 0)),
        E(arrayOf(Vec2(1, -1), Vec2(1, 0), Vec2(1, 1)), Vec2(1, 0)),
    }

    private fun parse(input: String): HashSet<Vec2> {
        val elves = hashSetOf<Vec2>()
        input.lines().forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == '#') elves.add(Vec2(x, y))
            }
        }
        return elves
    }

    private val neighborOffsets = listOf(
        Vec2(-1, -1),
        Vec2(0, -1),
        Vec2(1, -1),
        Vec2(-1, 0),
        Vec2(1, 0),
        Vec2(-1, 1),
        Vec2(0, 1),
        Vec2(1, 1),
    )

    private fun simulate(
        input: String,
        onRound: (elves: Set<Vec2>, round: Int, moved: Boolean) -> Int?,
    ): Int {
        var elves = parse(input)
        val dirs = ArrayDeque(listOf(Dir.N, Dir.S, Dir.W, Dir.E))

        var round = 0

        while (true) {
            round++
            val proposals = HashMap<Vec2, Vec2>(elves.size)
            val counts = HashMap<Vec2, Int>(elves.size)

            for (elf in elves) {
                if (neighborOffsets.none { (elf + it) in elves }) continue

                for (dir in dirs) {
                    if (dir.checks.none { (elf + it) in elves }) {
                        val target = elf + dir.move
                        proposals[elf] = target
                        counts[target] = counts.getOrDefault(target, 0) + 1
                        break
                    }
                }
            }

            val moved = proposals.isNotEmpty()
            val newElves = HashSet<Vec2>(elves.size)

            for (elf in elves) {
                val move = proposals[elf]
                if (move != null && counts[move] == 1) {
                    newElves.add(move)
                } else {
                    newElves.add(elf)
                }
            }

            elves = newElves
            dirs.addLast(dirs.removeFirst())

            val result = onRound(elves, round, moved)
            if (result != null) return result
        }
    }

    override fun part1(input: String): Int = simulate(input) { elves, round, _ ->
        if (round == 10) {
            var minX = Int.MAX_VALUE
            var maxX = Int.MIN_VALUE
            var minY = Int.MAX_VALUE
            var maxY = Int.MIN_VALUE

            for (e in elves) {
                if (e.x < minX) minX = e.x
                if (e.x > maxX) maxX = e.x
                if (e.y < minY) minY = e.y
                if (e.y > maxY) maxY = e.y
            }

            val area = (maxX - minX + 1) * (maxY - minY + 1)
            area - elves.size
        } else {
            null
        }
    }

    override fun part2(input: String): Int = simulate(input) { _, round, moved ->
        if (!moved) round else null
    }
}
