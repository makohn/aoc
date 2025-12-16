class Day06 : Day<Long, Long>(year = 2025, day = 6) {

    override fun part1(input: String): Long {
        val lines = input.lines()
        val ops = lines.takeLast(1).map { it.trim().split(Regex("\\s+")) }[0]
        val nums = lines.dropLast(1).map { it.trim().split(Regex("\\s+")).map { n -> n.toLong() } }
        var sum = 0L
        for (i in 0..<ops.size) {
            sum += nums.map { it[i] }.reduce { a, b -> when(ops[i]) {
                "*" -> a * b
                "+" -> a + b
                else -> error("Illegal operation!")
            }}
        }
        return sum
    }

    override fun part2(input: String): Long {
        val lines = input.lines()
        val (n, m) = lines.size to lines.maxOf { it.length }
        val grid = Array(lines.size) { idx -> lines[idx].toCharArray(CharArray(m)) }
        var sum = 0L
        var curVec = Array(m) { "" }
        var curOp = ""
        for (j in m-1 downTo 0) {
            var charDetected = false
            for (i in 0..<n) {
                val c = grid[i][j]
                if (c.isDigit()) {
                    curVec[j] += c
                    charDetected = true
                } else if (c == '*' || c == '+') {
                    curOp = c.toString()
                }
            }
            if ((j == 0 || !charDetected) && curOp.isNotBlank()) {
                val nums = curVec.filter { it.isNotEmpty() }.map { it.toLong() }
                sum += nums.reduce { a, b -> when(curOp) {
                    "*" -> a * b
                    "+" -> a + b
                    else -> error("Illegal operation: '$curOp'")
                }}
                curVec = Array(m) { "" }
                curOp = ""
            }
        }
        return sum
    }
}

fun main() = Day06().run {
    println(part1(input))
    println(part2(input))
}