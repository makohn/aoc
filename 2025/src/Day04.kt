fun main() {

    fun part1(input: List<String>): Int {
        val grid = Array(input.size) { idx -> input[idx].toCharArray() }
        val (n, m) = grid.size to grid[0].size
        var sum = 0
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (grid[i][j] == '@') {
                    var count = 0
                    for (a in -1..1) for (b in -1..1) {
                        val ai = i + a
                        val bj = j + b
                        if (ai in 0..<n && bj in 0..<m && !(ai == i && bj == j) && grid[ai][bj] == '@') {
                            count++
                        }
                    }
                    if (count < 4) sum++
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val grid = Array(input.size) { idx -> input[idx].toCharArray() }
        val (n, m) = grid.size to grid[0].size
        var prevSum = 0
        var sum = 0
        while (true) {
            for (i in 0..<n) {
                for (j in 0..<m) {
                    if (grid[i][j] == '@') {
                        var count = 0
                        for (a in -1..1) for (b in -1..1) {
                            val ai = i + a
                            val bj = j + b
                            if (ai in 0..<n && bj in 0..<m && !(ai == i && bj == j) && grid[ai][bj] == '@') {
                                count++
                            }
                        }
                        if (count < 4) {
                            grid[i][j] = 'X'
                            sum++
                        }
                    }
                }
            }
            if (prevSum == sum) break
            prevSum = sum
        }

        return sum
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}