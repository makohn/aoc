
fun main() {

    val day = "03"

    fun part1(input: List<String>): Int {
        return Regex("mul\\((\\d+),(\\d+)\\)")
            .findAll(input.joinToString())
            .map { it.groupValues }
            .sumOf { it[1].toInt() * it[2].toInt() }
    }

    fun part2(input: List<String>): Int {
        val str = input.joinToString()
        var inp = str
        var acc = 0
        var enabled = true
        for (i in str.indices) {
            inp = str.substring(i, str.length)
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    val testInput2 = readInput("Day${day}_test2")
    check(part1(testInput) == 161)
    check(part2(testInput2) == 48)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}