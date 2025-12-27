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
        val grid = input.lines().map { it.map { c -> c.code }.toIntArray() }.toTypedArray()
        val (n, m) = grid.size2
        val di = intArrayOf(-1, 0, 1, 0)
        val dj = intArrayOf(0, 1, 0, -1)

        fun solve(i: Int, j: Int, c: Int, r: Int): Int2 {
            grid[i][j] = r
            var a = 1
            var sides = 0
            for (x in 0..3) {
                val ii = i + di[x]
                val jj = j + dj[x]
                val inGrid = ii in 0..<n && jj in 0..<m
                if (inGrid && grid[ii][jj] == c) {
                    val (na, np) = solve(ii, jj, c, r)
                    a += na
                    sides += np
                }
                if (!inGrid || (grid[ii][jj] != c && grid[ii][jj] != r)) {
                    val rx = (x + 1).mod(4)
                    val lx = (x - 1).mod(4)
                    val rdi = di[rx]
                    val rdj = dj[rx]
                    val ldi = di[lx]
                    val ldj = dj[lx]
                    val ri = i + rdi
                    val rj = j + rdj
                    val li = i + ldi
                    val lj = j + ldj
                    val rii = ii + rdi
                    val rjj = jj + rdj
                    val lii = ii + ldi
                    val ljj = jj + ldj
                    if (
                        !(ri in 0..<n && rj in 0..<m && (grid[ri][rj] == c || grid[ri][rj] == r)) ||
                        (rii in 0..<n && rjj in 0..<m && (grid[rii][rjj] == c || grid[rii][rjj] == r))
                    ) sides++
                    if (
                        !(li in 0..<n && lj in 0..<m && (grid[li][lj] == c || grid[li][lj] == r)) ||
                        (lii in 0..<n && ljj in 0..<m && (grid[lii][ljj] == c || grid[lii][ljj] == r))
                    ) sides++
                }
            }
            return Int2(a, sides)
        }

        val r = -1
        var sum = 0
        var x = r
        for (i in 0..<n) for (j in 0..<m) {
            val c = grid[i][j]
            if (c <= r) continue
            val (a, sides) = solve(i, j, c, x)
            x--
            sum += a * (sides/2)
        }
        return sum
    }
}

fun main() = Day12().run {
    println(part1(input))
    println(part2(input))
}