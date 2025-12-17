class Day04 : Day<Int, Int>(year = 2024, day = 4) {

    override fun part1(input: String): Int {
        val grid = input.lines().toCharArray2()
        val (n, m) = grid.size2
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

    override fun part2(input: String): Int {
        val grid = input.lines().toCharArray2()
        val (n, m) = grid.size2
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
}

fun main() = Day04().run {
    println(part1(input))
    println(part2(input))
}