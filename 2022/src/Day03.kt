class Day03 : Day<Int, Int>(year = 2022, day = 3) {
    
    override fun part1(input: String): Int {
        val items = mutableListOf<Char>()
        for (r in input.lines()) {
            outer@for (c in 0 until r.length/2) {
                for (c2 in r.length/2 until r.length) {
                    if (r[c] == r[c2]) {
                        items.add(r[c])
                        break@outer
                    }
                }
            }
        }
        return items.sumOf { if (it.isUpperCase()) it - 'A' + 27 else it - 'a' + 1 }
    }

    override fun part2(input: String): Int {
        val inputLines = input.lines()
        val scores = mutableListOf<Int>()
        for (r in inputLines.indices step 3) {
            val s1 = inputLines[r].toList().toSet()
            val s2 = inputLines[r+1].toList().toSet()
            val s3 = inputLines[r+2].toList().toSet()
            val res = s1.intersect(s2).intersect(s3)
            scores.add(res.map { if (it.isUpperCase()) it - 'A' + 27 else it - 'a' + 1 }[0])
        }
        return scores.sum()
    }
}

fun main() = Day03().run {
    println(part1(input))
    println(part2(input))
}