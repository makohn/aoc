import util.iter.transpose

class Day03 : Day<Int, Int>(year = 2021, day = 3) {

    override fun part1(input: String): Int = input
        .lines()
        .map { it.toList() }
        .transpose()
        .map { list -> list.groupingBy { it }.eachCount() }
        .map { map -> listOf(map.maxByOrNull { it.value }?.key, map.minByOrNull { it.value }?.key) }
        .transpose()
        .map { it.joinToString("").toInt(2) }
        .reduce(Int::times)

    override fun part2(input: String): Int {
        val lines = input.lines()
        val list = lines.map { it.toInt(2) }
        var a = list
        var b = list
        val max = input.lines().first().count()
        var pos = 0
        while ((a.size > 1) and (pos < max)) {
            val x = 1 shl max-1 shr pos
            val (l1, l2) = a.partition { (it and x) == x }
            a = if (l1.size >= l2.size) l1 else l2
            pos += 1
        }
        pos = 0
        while ((b.size > 1) and (pos < max)) {
            val x = 1 shl max-1 shr pos
            val (l1, l2) = b.partition { (it and x) == x }
            b = if (l1.size >= l2.size) l2 else l1
            pos += 1
        }
        return listOf(a.first(), b.first()).reduce(Int::times)
    }
}

fun main() = Day03().run {
    println(part1(input))
    println(part2(input))
}
