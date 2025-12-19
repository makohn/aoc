class Day06 : Day<Int, Int>(year = 2024, day = 6) {

    override fun part1(input: String): Int {
        val grid = input.lines().toCharArray2()
        val (n, m) = grid.size2
        var p = grid.positionOf('^')
        var d = Int2(-1, 0)
        var c = 1
        while (true) {
            val (i, j) = p
            val (di, dj) = p + d
            if (grid[i][j] == '.') c++
            if (di in 0..<n && dj in 0..<m) {
                if (grid[di][dj] == '#') d = Int2(d.y, -d.x)
                grid[i][j] = 'X'
                p += d
            } else break

        }
        return c
    }

    override fun part2(input: String): Int {
        val startGrid = input.lines().toCharArray2()
        val (n, m) = startGrid.size2
        val sp = startGrid.positionOf('^')
        val sd = Int2(-1, 0)

        fun solve(grid: CharArray2, start: Int2, dir: Int2, first: Boolean): Int {
            val seen = HashSet<Int4>()
            var p = start
            var d = dir
            var c = 0
            while (true) {
                val (i, j) = p
                val (di, dj) = p + d
                val v = Int4(i, j, d.x, d.y)
                if (v in seen) return 1 else seen.add(v)
                if (di in 0..<n && dj in 0..<m) {
                    if (grid[di][dj] == '#') {
                        d = Int2(d.y, -d.x)
                    } else {
                        if (first && grid[di][dj] == '.') {
                            grid[di][dj] = '#'
                            c += solve(grid, p, d, false)
                            grid[di][dj] = 'X'
                        }
                        p += d
                    }
                } else return c
            }
        }
        return solve(startGrid, sp, sd, true)
    }
}

fun main() = Day06().run {
    println(part1(input))
    println(part2(input))
}