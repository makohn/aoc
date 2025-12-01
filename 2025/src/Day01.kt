fun main() {

    fun part1(input: List<String>): Int {
        var dial = 50
        var c = 0
        for (ins in input) {
            val (d, n) = ins.take(1) to ins.drop(1).toInt()
            dial = if (d == "R") (dial + n).mod(100) else (dial - n).mod(100)
            if (dial == 0) c++
        }
        return c
    }

    fun part2(input: List<String>): Int {
        var dial = 50
        var c = 0
        for (ins in input) {
            val (d, n) = ins.take(1) to ins.drop(1).toInt()
            val dl = if (d == "R") (dial + n) else (dial - n)
            c += if (dl > 0) dl / 100 else (-dl / 100) + if(dial == 0) 0 else 1
            dial = dl.mod(100)
        }
        return c
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}