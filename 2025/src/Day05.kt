fun main() {

    fun part1(input: List<String>): Int {
        var fresh = 0
        val sep = input.indexOfFirst { it.isBlank() }
        val ranges = input.subList(0, sep).map {
            val (a, b) = it.split("-")
            a.toLong()..b.toLong()
        }
        val ingredients = input.subList(sep + 1, input.size).map { it.toLong() }
        nextIng@for (ingredient in ingredients) {
            for (range in ranges) {
                if (ingredient in range) {
                    fresh++
                    continue@nextIng
                }
            }
        }
        return fresh
    }

    fun part2(input: List<String>): Long {
        val sep = input.indexOfFirst { it.isBlank() }
        val ranges = input.subList(0, sep).map {
            val (a, b) = it.split("-")
            a.toLong()..b.toLong()
        }
        val dr = ArrayList<LongRange>()
        nextRange@for (range in ranges) {
            var x = range
            val rem = ArrayList<LongRange>()
            for (r in dr) {
                if (x.first >= r.first && x.last <= r.last) {
                    continue@nextRange
                } else if (r.first >= x.first && r.last <= x.last) {
                    rem.add(r)
                } else if (x.first in r.first..r.last) {
                    x = r.last+1..x.last
                } else if (r.first in x.first..x.last) {
                    x = x.first..<r.first
                }
            }
            for (t in rem) {
                dr.remove(t)
            }
            dr.add(x)
        }
        val res = dr.fold(0L) { acc, r -> acc + (1 + r.last - r.first) }
        return res
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}