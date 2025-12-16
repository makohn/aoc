
class Day03() : Day<Int, Int>(year = 2024, day = 3) {

    override fun part1(input: String): Int {
        return Regex("mul\\((\\d+),(\\d+)\\)")
            .findAll(input)
            .map { it.groupValues }
            .sumOf { it[1].toInt() * it[2].toInt() }
    }

    override fun part2(input: String): Int {
        var acc = 0
        var enabled = true
        var inp: String
        for (i in input.indices) {
            inp = input.substring(i, input.length)
            if (inp.startsWith("do()")) {
                enabled = true
            }
            if (inp.startsWith("don't()")) {
                enabled = false
            }
            val res = Regex("mul\\((\\d+),(\\d+)\\)").matchAt(inp, 0)?.groupValues
            res?.let {
                if (enabled) {
                    acc += it[1].toInt() * it[2].toInt()
                }
            }
        }
        return acc
    }
}

fun main() = Day03().run {
    println(part1(input))
    println(part2(input))
}