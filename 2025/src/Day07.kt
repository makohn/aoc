class Day07 : Day<Int, Long>(year = 2025, day = 7) {

    override fun part1(input: String): Int {
        val inputLines = input.lines()
        val beams = HashSet<Int>()
        beams.add(inputLines[0].indexOf('S'))
        var numSplit = 0
        for (row in inputLines.drop(1)) {
            val splitters = row.withIndex().filter { (_, c) -> c == '^' }.map { it.index }
            for (s in splitters) {
                if (s in beams) {
                    beams.add(s-1)
                    beams.add(s+1)
                    beams.remove(s)
                    numSplit++
                }
            }
        }
        return numSplit
    }

    override fun part2(input: String): Long {
        val inputLines = input.lines()
        val beamCounts = HashMap<Int, Long>()
        val startIndex = inputLines[0].indexOf('S')
        beamCounts[startIndex] = 1
        for (row in inputLines.drop(1)) {
            val newCounts = HashMap<Int, Long>()
            for ((col, count) in beamCounts) {
                if (row[col] == '^') {
                    newCounts[col - 1] = newCounts.getOrDefault(col - 1, 0) + count
                    newCounts[col + 1] = newCounts.getOrDefault(col + 1, 0) + count
                } else {
                    newCounts[col] = newCounts.getOrDefault(col, 0) + count
                }
            }
            beamCounts.clear()
            beamCounts.putAll(newCounts)
        }
        return beamCounts.values.sum()
    }
}

fun main() = Day07().run {
    println(part1(input))
    println(part2(input))
}