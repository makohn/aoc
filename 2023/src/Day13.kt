class Day13 : Day<Int, Int>(year = 2023, day = 13) {

    override fun part1(input: String): Int {
        fun verticalMirrorPosition(pattern: CharGrid): Int {
            val (n, m) = pattern.shape
            moveMirror@for (mirror in 1..<m) {
                for (margin in 0..m) {
                    val left = mirror - 1 - margin
                    val right = mirror + margin
                    if (left in 0..<mirror && right in mirror..<m) {
                        for (row in 0..<n) {
                            if (pattern[row][left] != pattern[row][right]) continue@moveMirror
                        }
                    }
                }
                return mirror
            }
            return 0
        }

        fun horizontalMirrorPosition(pattern: CharGrid): Int {
            val (n, m) = pattern.shape
            moveMirror@for (mirror in 1..<n) {
                for (margin in 0..n) {
                    val above = mirror - 1 - margin
                    val below = mirror + margin
                    if (above in 0..<mirror && below in mirror..<n) {
                        for (col in 0..<m) {
                            if (pattern[above][col] != pattern[below][col]) continue@moveMirror
                        }
                    }
                }
                return mirror
            }
            return 0
        }

        return input
            .split("\n\n")
            .map { it.split("\n").toCharGrid() }
            .sumOf { verticalMirrorPosition(it) + 100*horizontalMirrorPosition(it) }
    }

    override fun part2(input: String): Int {
        fun verticalMirrorPosition(pattern: CharGrid): Int {
            val (n, m) = pattern.shape
            for (mirror in 1..<m) {
                var error = 0
                for (margin in 0..m) {
                    val left = mirror - 1 - margin
                    val right = mirror + margin
                    if (left in 0..<mirror && right in mirror..<m) {
                        for (row in 0..<n) {
                            if (pattern[row][left] != pattern[row][right]) error++
                        }
                    }
                }
                if (error == 1) return mirror
            }
            return 0
        }

        fun horizontalMirrorPosition(pattern: CharGrid): Int {
            val (n, m) = pattern.shape
            for (mirror in 1..<n) {
                var error = 0
                for (margin in 0..n) {
                    val above = mirror - 1 - margin
                    val below = mirror + margin
                    if (above in 0..<mirror && below in mirror..<n) {
                        for (col in 0..<m) {
                            if (pattern[above][col] != pattern[below][col]) error++
                        }
                    }
                }
                if (error == 1) return mirror
            }
            return 0
        }

        return input
            .split("\n\n")
            .map { it.split("\n").toCharGrid() }
            .sumOf { verticalMirrorPosition(it) + 100*horizontalMirrorPosition(it) }
    }
}

fun main() = Day13().run {
    println(part1(input))
    println(part2(input))
}