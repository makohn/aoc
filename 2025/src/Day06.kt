
fun main() {

    fun part1(input: List<String>): Long {
        val ops = input.takeLast(1).map { it.trim().split(Regex("\\s+")) }[0]
        val nums = input.dropLast(1).map { it.trim().split(Regex("\\s+")).map { n -> n.toLong() } }
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

    fun part2(input: List<String>): Long {
        val (n, m) = input.size to input.maxOf { it.length }
        val grid = Array(input.size) { idx -> input[idx].toCharArray(CharArray(m)) }
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

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 3263827L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}