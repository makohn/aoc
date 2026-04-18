package year2024

import util.core.*
import util.parse.*
import util.thread.*

class Day22 : Solution<Long, Int>(year = 2024, day = 22) {

    companion object {
        const val MASK = (1 shl 24) - 1
        const val MAX_SEQUENCES = 19 * 19 * 19 * 19
    }

    private fun Int.hash(): Int {
        var s = this
        s = (s xor (s shl 6)) and MASK
        s = (s xor (s ushr 5)) and MASK
        s = (s xor (s shl 11)) and MASK
        return s
    }

    private fun buildTransform(): LongArray {
        val transform = LongArray(24)
        for (i in 0..<24) {
            val basis = 1 shl i
            val transformed = basis.hash()
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

    private fun indexOf(previous: Int, current: Int) = 9 + current % 10 - previous % 10

    private fun process(numbers: List<Int>): IntArray {
        val seen = IntArray(MAX_SEQUENCES) { Int.MAX_VALUE }
        val res = IntArray(MAX_SEQUENCES) { 0 }

        for (i in numbers.indices) {
            val n0 = numbers[i]
            val n1 = n0.hash()
            val n2 = n1.hash()
            val n3 = n2.hash()

            var a: Int
            var b = indexOf(n0, n1)
            var c = indexOf(n1, n2)
            var d = indexOf(n2, n3)

            var number = n3
            var previous = n3 % 10

            repeat(1997) {
                number = number.hash()
                val price = number % 10

                a = b
                b = c
                c = d
                d = indexOf(previous, price)
                val index = 6859 * a + 361 * b + 19 * c + d
                previous = price

                if (seen[index] != i) {
                    res[index] += price
                    seen[index] = i
                }
            }
        }
        return res
    }

    override fun part1(input: String): Long {
        val numbers = input.extractLongs()
        val transform = buildTransform().pow(2000)
        var sum = 0L
        for (n in numbers) {
            var res = 0
            for (bit in 0..<24) {
                val v = transform[bit] and n
                if (v.countOneBits() % 2 == 1) res = res or (1 shl bit)
            }
            sum += res
        }
        return sum
    }

    override fun part2(input: String): Int = input
        .extractInts()
        .inParallel { process(it) }
        .fold(IntArray(MAX_SEQUENCES)) { acc, part ->
            for (i in acc.indices) acc[i] += part[i]
            acc
        }.max()
}
