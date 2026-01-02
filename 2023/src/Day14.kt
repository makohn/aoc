class Day14 : Day<Int, Int>(year = 2023, day = 14) {

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val (n, m) = grid.shape

        var ans = 0
        for (j in 0..<m) {
            var rocksToRoll = 0
            for (i in n-1 downTo 0) {
                when (grid[i][j]) {
                    '#' -> {
                        for (x in 0..<rocksToRoll) {
                            ans += n - (i+x+1)
                        }
                        rocksToRoll = 0
                    }
                    'O' -> rocksToRoll++
                }
            }
            for (x in 0..<rocksToRoll) {
                ans += n - x
            }
        }
        return ans
    }

    fun simulateRolling(grid: CharGrid): CharGrid {
        val (n, m) = grid.shape
        for (j in 0..<m) {
            for (i in 0..<n) {
                for (x in 0..<n) {
                    if (grid[x][j] == 'O' && x > 0 && grid[x-1][j] == '.') {
                        grid[x][j] = '.'
                        grid[x-1][j] = 'O'
                    }
                }
            }
        }
        return grid
    }

    fun calculateLoad(grid: CharGrid): Int {
        val (n, m) = grid.shape

        var ans = 0
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (grid[i][j] == 'O') ans += n-i
            }
        }
        return ans
    }

    override fun part2(input: String): Int {
        var grid = input.lines().toCharGrid()

        var pos: Pair<Int, Int>? = null
        val seen = mutableMapOf<String, Int>()
        val seenReversed = mutableMapOf<Int, String>()
        seenReversed[0] = grid.rowsToString()
        seen[grid.rowsToString()] = 0
        iterate@for(cycle in 0..1000000000) {
            repeat(4) {
                grid = simulateRolling(grid)
                grid = grid.rotated()
            }
            val strMap = grid.rowsToString()
            if (strMap in seen) {
                pos = cycle+1 to seen[strMap]!!
                break@iterate
            }
            seen[strMap] = cycle+1
            seenReversed[cycle+1] = grid.rowsToString()
        }
        val a = (1000000000 - pos!!.second)
        val b = (pos.first - pos.second)
        val c = (a % b) + pos.second
        val d = seenReversed[c]!!
        val e = d.split("\n").toCharGrid()
        return calculateLoad(e)
    }
}

fun main() = Day14().run {
    println(part1(input))
    println(part2(input))
}