package year2022

import util.core.*

class Day17 : Solution<Int, Long> {

    private data class Rock(val shape: Int, val height: Int)

    companion object {
        private val ROCKS = arrayOf(
            Rock(0b00000000_00000000_00000000_00111100, 1),
            Rock(0b00000000_00010000_00111000_00010000, 3),
            Rock(0b00000000_00001000_00001000_00111000, 3),
            Rock(0b00100000_00100000_00100000_00100000, 4),
            Rock(0b00000000_00000000_00110000_00110000, 2),
        )
        private const val BORDER = 0b00000001_00000001_00000001_00000001
        private const val MAX_TOWER_SIZE = 13_000
    }

    private inline fun Int.littleEndianBits(fn: (Int, Int) -> Unit) {
        fn(0, this and 0xff)
        fn(1, (this shr 8) and 0xff)
        fn(2, (this shr 16) and 0xff)
        fn(3, (this shr 24) and 0xff)
    }

    private fun IntArray.orAssign(index: Int, value: Int) {
        this[index] = this[index] or value
    }

    private fun initTower(): IntArray {
        val tower = IntArray(MAX_TOWER_SIZE)
        tower[0] = 0xff
        return tower
    }

    private class Stream<T>(private val array: Array<T>) {
        var index = 0
            private set
        fun next(): T {
            val next = array[index]
            index = (index + 1) % array.size
            return next
        }
    }

    private class StringStream(private val string: String) {
        var index = 0
            private set
        fun next(): Char {
            val next = string[index]
            index = (index + 1) % string.length
            return next
        }
    }

    private inner class Simulation(input: String) {
        val tower = initTower()
        val rocks = Stream(ROCKS)
        val jets = StringStream(input)
        var height = 0
            private set

        fun dropRock(): Boolean {
            val rock = rocks.next()
            var shape = rock.shape
            var collisionMask = BORDER
            var y = height + 3
            var jetWrapped = false
            while (true) {
                val jet = jets.next()
                if (jets.index == 0) jetWrapped = true
                val shiftedShape = if (jet == '<') shape.rotateLeft(1) else shape.rotateRight(1)
                if (shiftedShape and collisionMask == 0) shape = shiftedShape
                collisionMask = (collisionMask shl 8) or BORDER or tower[y]
                if (shape and collisionMask != 0) {
                    shape.littleEndianBits { index, bit -> tower.orAssign(y + index + 1, bit) }
                    height = maxOf(height, y + rock.height)
                    break
                }
                y--
            }
            return jetWrapped
        }
    }

    override fun part1(input: String): Int {
        val simulation = Simulation(input)
        val totalRockCount = 2022
        var rockCount = 0
        while (rockCount++ < totalRockCount) {
            simulation.dropRock()
        }
        return simulation.height
    }

    override fun part2(input: String): Long {
        val simulation = Simulation(input)

        val totalRockCount = 1_000_000_000_000
        var targetRockCount = totalRockCount
        var rockCount = 0L

        val seen = BooleanArray(input.length)
        var bottomRockCount = 0L
        var bottomHeight = 0
        var repeatIndex = -1
        var repeatHeight = 0
        var repeatRockCount = 0L

        while (rockCount++ < targetRockCount) {
            val jetWrapped = simulation.dropRock()
            simulation.run {
                if (rocks.index == 0 && jetWrapped) {
                    if (repeatIndex < 0) {
                        if (seen[jets.index]) {
                            bottomRockCount = rockCount
                            bottomHeight = height
                            repeatIndex = jets.index
                        } else {
                            seen[jets.index] = true
                        }
                    } else if (repeatIndex == jets.index) {
                        repeatHeight = height - bottomHeight
                        repeatRockCount = rockCount - bottomRockCount
                        targetRockCount = rockCount + (totalRockCount - bottomRockCount) % repeatRockCount
                    }
                }
            }
        }
        val modHeight = simulation.height - bottomHeight - repeatHeight
        val repeatCount = if (repeatRockCount > 0) (totalRockCount - bottomRockCount) / repeatRockCount else 0
        val ans = bottomHeight + repeatCount * repeatHeight + modHeight
        return ans
    }
}
