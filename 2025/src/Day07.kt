
fun main() {

    fun part1(input: List<String>): Int {
        val beams = HashSet<Int>()
        beams.add(input[0].indexOf('S'))
        var numSplit = 0
        for (row in input.drop(1)) {
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

    fun part2(input: List<String>): Long {
        val beamCounts = HashMap<Int, Long>()
        val startIndex = input[0].indexOf('S')
        beamCounts[startIndex] = 1
        for (row in input.drop(1)) {
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

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 40L)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}