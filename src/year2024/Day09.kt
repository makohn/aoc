package year2024

import util.core.*

class Day09 : Solution<Long, Long>(year = 2024, day = 9) {

    companion object {
        private const val FREE = -1L
    }

    override fun part1(input: String): Long {
        val layout = input.map { it.digitToInt() }
        val disk = LongArray(layout.sum())
        var i = 0
        var idx = 0L
        var t = true
        for (number in layout) {
            var n = number
            while (n > 0) {
                disk[i] = if (t) idx else FREE
                i++
                n--
            }
            if (t) idx++
            t = !t
        }
        var s = 0
        var e = disk.lastIndex
        while (s < e) {
            while (disk[s] != FREE && s < e) s++
            while (disk[e] == FREE && e > s) e--
            disk[s] = disk[e]
            disk[e] = -1
        }
        return disk.reduceIndexed { index, acc, i -> if (i == FREE) acc else acc + index * i }
    }

    private data class Block(val id: Long, val size: Int)

    override fun part2(input: String): Long {
        val blocks = input
            .mapIndexed { idx, c -> Block(if (idx % 2 == 0) idx/2L else FREE, c.digitToInt()) }
            .toMutableList()
        var s = 0
        var e = blocks.lastIndex
        while (e >= 0) {
            while (blocks[e].id == FREE) e--
            while (!(blocks[s].id == FREE && blocks[s].size >= blocks[e].size) && s <= e) s++
            if (s >= e) {
                s = 0
                e--
                continue
            }
            val size = blocks[e].size
            val diff = blocks[s].size - size
            blocks[s] = blocks[e]
            if (diff > 0) {
                blocks.add(s + 1, Block(FREE, diff))
                e += 1
            }
            blocks[e] = Block(FREE, size)
            s = 0
        }
        var sum = 0L
        var pos = 0
        for (block in blocks) {
            val newPos = pos + block.size
            if (block.id != FREE) {
                for (p in pos..<newPos) {
                    sum += block.id * p
                }
            }
            pos = newPos
        }
        return sum
    }
}

fun main() = Day09().run {
    println(part1(input))
    println(part2(input))
}