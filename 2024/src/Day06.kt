class Day06 : Day<Int, Int>(year = 2024, day = 6) {

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val (n, m) = grid.shape
        var p = grid.positionOf('^')
        var d = Point(-1, 0)
        var c = 1
        while (true) {
            val (i, j) = p
            val (di, dj) = p + d
            if (grid[i][j] == '.') c++
            if (di in 0..<n && dj in 0..<m) {
                if (grid[di][dj] == '#') d = Point(d.j, -d.i)
                grid[i][j] = 'X'
                p += d
            } else break

        }
        return c
    }

    override fun part2(input: String): Int {
        val startGrid = input.lines().toCharGrid()
        val (n, m) = startGrid.shape
        val (si, sj) = startGrid.positionOf('^')
        val sd = 0
        val dirs = arrayOf(Point(-1, 0), Point(0, 1), Point(1, 0), Point(0, -1))
        val seen = Array(4) { Array(n) { BooleanArray(m) } }

        fun solve(grid: CharGrid, i: Int, j: Int, dir: Int, first: Boolean): Int {
            seen.forEach { arr -> arr.forEach { it.fill(false) } }
            var ii = i
            var jj = j
            var d = dir
            var c = 0
            while (true) {
                val di = ii + dirs[d].i
                val dj = jj + dirs[d].j
                if (seen[d][ii][jj]) return 1 else seen[d][ii][jj] = true
                if (di in 0..<n && dj in 0..<m) {
                    if (grid[di][dj] == '#') d = (d + 1) % 4
                    else {
                        if (first && grid[di][dj] == '.') {
                            grid[di][dj] = '#'
                            c += solve(grid, ii, jj, d, false)
                            grid[di][dj] = 'X'
                        }
                        ii = di
                        jj = dj
                    }
                } else return c
            }
        }
        return solve(startGrid, si, sj, sd, true)
    }
}

fun main() = Day06().run {
    println(part1(input))
    println(part2(input))
}