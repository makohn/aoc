class Day12 : Day<Int, Int>(year = 2024, day = 12) {

    override fun part1(input: String): Int {
        val grid = input.lines().map { it.map { c -> c.code }.toIntArray() }.toTypedArray()
        val (n, m) = grid.size2
        val di = intArrayOf(-1, 0, 1, 0)
        val dj = intArrayOf(0, 1, 0, -1)

        fun solve(i: Int, j: Int, c: Int, r: Int): Int2 {
            grid[i][j] = r
            var a = 1
            var p = 0
            for (x in 0..3) {
                val ii = i + di[x]
                val jj = j + dj[x]
                val inGrid = ii in 0..<n && jj in 0..<m
                if (inGrid && grid[ii][jj] == c) {
                    val (na, np) = solve(ii, jj, c, r)
                    a += na
                    p += np
                }
                if (!inGrid || (grid[ii][jj] != c && grid[ii][jj] != r)) p++
            }
            return Int2(a, p)
        }

        val r = -1
        var sum = 0
        var x = r
        for (i in 0..<n) for (j in 0..<m) {
            val c = grid[i][j]
            if (c <= r) continue
            val (a, p) = solve(i, j, c, x)
            x--
            sum += a * p
        }
        return sum
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day12().run {
    println(part1(input))
}