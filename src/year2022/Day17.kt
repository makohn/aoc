package year2022

import util.core.*

class Day17 : Solution<Int, Long>(year = 2022, day = 17) {

    private data class Rock(val shape: Int, val height: Int)

    companion object {
        private val ROCKS = arrayOf(
            Rock(0b00000000_00000000_00000000_00111100, 1),
            Rock(0b00000000_00010000_00111000_00010000, 3),
            Rock(0b00000000_00001000_00001000_00111000, 3),
            Rock(0b00100000_00100000_00100000_00100000, 4),
            Rock(0b00000000_00000000_00110000_00110000, 2)
        )
        private const val BORDER = 0b00000001_00000001_00000001_00000001
    }

    override fun part1(input: String): Int {
        var height = 0
        val tower = IntArray(13000)
        var j = 0
        tower[0] = 0xff
        repeat(2022) { k ->
            val rock = ROCKS[k % 5]
            var shape = rock.shape
            var collider = BORDER
            var pos = height + 3
            while (true) {
                val jet = input[j++ % input.length]
                val pushedShape = if (jet == '<') shape.rotateLeft(1) else shape.rotateRight(1)
                if (pushedShape and collider == 0) shape = pushedShape
                collider = (collider shl 8) or BORDER or tower[pos]
                if (shape and collider != 0) {
                    for (j in 0..3) {
                        val mask = tower[pos + j + 1]
                        tower[pos + j + 1] = mask or (shape shr 8 * j) and 0xff
                    }
                    height = maxOf(height, pos + rock.height)
                    break
                } else pos--
            }
        }
        return height
    }

    override fun part2(input: String): Long {
        var height = 0
        val tower = IntArray(13000)
        val seen = HashSet<Int>()
        var repeatIndex: Int? = null
        var numRocks = 0L
        var bottomHeight = 0
        var repeatHeight = 0
        var repeatNumRocks = 0L
        var j = 0
        tower[0] = 0xff
        var k = -1L
        val totalRocks = 1000000000000
        var n = totalRocks
        while (++k < n) {
            val ri = (k % 5).toInt()
            val rock = ROCKS[ri]
            var shape = rock.shape
            var collider = BORDER
            var pos = height + 3
            var jetWrapped = false
            while (true) {
                val jet = input[j]
                j = (j + 1) % input.length
                if (j == 0) jetWrapped = true
                val pushedShape = if (jet == '<') shape.rotateLeft(1) else shape.rotateRight(1)
                if (pushedShape and collider == 0) shape = pushedShape
                collider = (collider shl 8) or BORDER or tower[pos]
                if (shape and collider != 0) {
                    for (j in 0..3) {
                        val mask = tower[pos + j + 1]
                        tower[pos + j + 1] = mask or (shape shr 8 * j) and 0xff
                    }
                    height = maxOf(height, pos + rock.height)
                    break
                } else pos--
            }
            val rii = (ri + 1) % 5
            if (rii == 0 && jetWrapped) {
                if (repeatIndex == null) {
                    if (j in seen) {
                        numRocks = k
                        bottomHeight = height
                        repeatIndex = j
                    } else {
                        seen += j
                    }
                } else if (repeatIndex == j) {
                    repeatHeight = height - bottomHeight
                    repeatNumRocks = k - numRocks
                    n = k + (totalRocks - numRocks) % repeatNumRocks
                }
            }
        }
        val modHeight = height - bottomHeight - repeatHeight
        val repeatCount = if (repeatNumRocks > 0) (totalRocks - numRocks) / repeatNumRocks else 0
        val ans = bottomHeight + repeatCount * repeatHeight + modHeight
        return ans
    }
}