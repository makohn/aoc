package year2024

import util.core.*
import util.parse.extractLongs

class Day22 : Solution<Long, Long>(year = 2024, day = 22) {

    companion object {
        const val MASK = (1 shl 24) - 1
    }

    private fun step(n: Int): Int {
        var s = n
        s = (s xor (s shl 6)) and MASK
        s = (s xor (s ushr 5)) and MASK
        s = (s xor (s shl 11)) and MASK
        return s
    }

    private fun buildTransform(): LongArray {
        val transform = LongArray(24)
        for (i in 0..<24) {
            val basis = 1 shl i
            val transformed = step(basis)
            for (bit in 0..<24) {
                if ((transformed ushr bit) and 1 == 1) {
                    transform[bit] = transform[bit] xor (1L shl i)
                }
            }
        }
        return transform
    }

    private operator fun LongArray.times(other: LongArray): LongArray {
        val res = LongArray(24)
        for (i in 0..<24) {
            var row = 0L
            for (bit in 0..<24) {
                if ((this[i] ushr bit) and 1 == 1L) {
                    row = row xor other[bit]
                }
            }
            res[i] = row
        }
        return res
    }

    private fun LongArray.pow(exp: Int): LongArray {
        var res = LongArray(24) { 1L shl it }
        var base = this
        var e = exp
        while (e > 0) {
            if (e and 1 == 1) res *= base
            base *= base
            e = e shr 1
        }
        return res
    }

    override fun part1(input: String): Long {
        val initialNumbers = input.extractLongs()
        val transform = buildTransform().pow(2000)
        var sum = 0L
        for (n in initialNumbers) {
            var res = 0
            for (bit in 0..<24) {
                val v = transform[bit] and n
                if (v.countOneBits() % 2 == 1) res = res or (1 shl bit)
            }
            sum += res
        }
        return sum
    }

    override fun part2(input: String): Long {
        return 0L
    }
}

fun main() = Day22().run {
    println(part1(input))
    println(part2(input))
}