package year2024

import util.core.*
import util.parse.extractInts
import util.parse.extractLongs

class Day22 : Solution<Long, Int>(year = 2024, day = 22) {

    companion object {
        const val MASK = (1 shl 24) - 1
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

    override fun part2(input: String): Int {
        val numbers = input.extractInts()
        val seen = IntArray(130_321) { Int.MAX_VALUE }
        val res = IntArray(130_321) { 0 }

        numbers.withIndex().toList().forEach { (i, number) ->
            val zero = number
            val first = zero.hash()
            val second = first.hash()
            val third = second.hash()

            var a: Int
            var b = indexOf(zero, first)
            var c = indexOf(first, second)
            var d = indexOf(second, third)

            var number = third
            var previous = third % 10

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
        return res.max()
    }
}

fun main() = Day22().run {
    println(part1(input))
    println(part2(input))
}