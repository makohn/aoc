class Day14 : Day<Int, Int>(year = 2023, day = 14) {

    override fun part1(input: String): Int {
        val map = input.lines().toCharMatrix()
        val (n, m) = map.dimension

        var ans = 0
        for (j in 0..<m) {
            var rocksToRoll = 0
            for (i in n-1 downTo 0) {
                when (map[i][j]) {
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

    fun simulateRolling(map: CharMatrix): CharMatrix {
        val (n, m) = map.dimension
        for (j in 0..<m) {
            for (i in 0..<n) {
                for (x in 0..<n) {
                    if (map[x][j] == 'O' && x > 0 && map[x-1][j] == '.') {
                        map[x][j] = '.'
                        map[x-1][j] = 'O'
                    }
                }
            }
        }
        return map
    }

    fun calculateLoad(map: CharMatrix): Int {
        val (n, m) = map.dimension

        var ans = 0
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (map[i][j] == 'O') ans += n-i
            }
        }
        return ans
    }

    override fun part2(input: String): Int {
        var map = input.lines().toCharMatrix()

        var pos: Pair<Int, Int>? = null
        val seen = mutableMapOf<String, Int>()
        val seenReversed = mutableMapOf<Int, String>()
        seenReversed[0] = map.asString()
        seen[map.asString("")] = 0
        iterate@for(cycle in 0..1000000000) {
            repeat(4) {
                map = simulateRolling(map)
                map = map.rotateClockwise()
            }
            val strMap = map.asString("")
            if (strMap in seen) {
                pos = cycle+1 to seen[strMap]!!
                break@iterate
            }
            seen[strMap] = cycle+1
            seenReversed[cycle+1] = map.asString()
        }
        val a = (1000000000 - pos!!.second)
        val b = (pos.first - pos.second)
        val c = (a % b) + pos.second
        val d = seenReversed[c]!!
        val e = d.split("\n").toCharMatrix()
        return calculateLoad(e)
    }
}

fun main() = Day14().run {
    println(part1(input))
    println(part2(input))
}