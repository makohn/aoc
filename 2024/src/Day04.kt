
fun main() {

    val day = "04"

    fun part1(input: List<String>): Int {
        val grid = input.toCharMatrix()
        val (n, m) = grid.dimension
        var acc = 0
        for (i in 0..< n) {
            for (j in 0..< m) {
                if (i + 3 < m && grid[i][j] == 'X' && grid[i+1][j] == 'M' && grid[i+2][j] == 'A' && grid[i+3][j] == 'S') acc++
                if (j + 3 < n && grid[i][j] == 'X' && grid[i][j+1] == 'M' && grid[i][j+2] == 'A' && grid[i][j+3] == 'S') acc++
                if (i + 3 < m && j + 3 < n && grid[i][j] == 'X' && grid[i+1][j+1] == 'M' && grid[i+2][j+2] == 'A' && grid[i+3][j+3] == 'S') acc++
                if (i - 3 >= 0 && j + 3 < n && grid[i][j] == 'X' && grid[i-1][j+1] == 'M' && grid[i-2][j+2] == 'A' && grid[i-3][j+3] == 'S') acc++

                if (i + 3 < m && grid[i][j] == 'S' && grid[i+1][j] == 'A' && grid[i+2][j] == 'M' && grid[i+3][j] == 'X') acc++
                if (j + 3 < n && grid[i][j] == 'S' && grid[i][j+1] == 'A' && grid[i][j+2] == 'M' && grid[i][j+3] == 'X') acc++
                if (i + 3 < m && j + 3 < n && grid[i][j] == 'S' && grid[i+1][j+1] == 'A' && grid[i+2][j+2] == 'M' && grid[i+3][j+3] == 'X') acc++
                if (i - 3 >= 0 && j + 3 < n && grid[i][j] == 'S' && grid[i-1][j+1] == 'A' && grid[i-2][j+2] == 'M' && grid[i-3][j+3] == 'X') acc++
            }
        }
        return acc
    }

    fun part2(input: List<String>): Int {
        val grid = input.toCharMatrix()
        val (n, m) = grid.dimension
        var acc = 0
        for (i in 0..< n) {
            for (j in 0..< m) {
                if (i + 2 < m && j + 2 < n && grid[i][j] == 'M' && grid[i+1][j+1] == 'A' && grid[i+2][j+2] == 'S' && grid[i+2][j] == 'S' && grid[i][j+2] == 'M') acc++
                if (i + 2 < m && j + 2 < n && grid[i][j] == 'M' && grid[i+1][j+1] == 'A' && grid[i+2][j+2] == 'S' && grid[i+2][j] == 'M' && grid[i][j+2] == 'S') acc++
                if (i + 2 < m && j + 2 < n && grid[i][j] == 'S' && grid[i+1][j+1] == 'A' && grid[i+2][j+2] == 'M' && grid[i+2][j] == 'S' && grid[i][j+2] == 'M') acc++
                if (i + 2 < m && j + 2 < n && grid[i][j] == 'S' && grid[i+1][j+1] == 'A' && grid[i+2][j+2] == 'M' && grid[i+2][j] == 'M' && grid[i][j+2] == 'S') acc++
            }
        }
        return acc
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}