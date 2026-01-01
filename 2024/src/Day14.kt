import util.parse.extractInts

class Day14(
    private val w: Int = 101,
    private val h: Int = 103
) : Day<Int, Int>(year = 2024, day = 14) {

    override fun part1(input: String): Int {
        val robots = input.extractInts().chunked(4)
        val res = IntArray(4)
        val xm = w / 2
        val ym = h / 2
        for ((px, py, vx, vy) in robots) {
            val npx = (px + 100 * vx).mod(w)
            val npy = (py + 100 * vy).mod(h)
            when {
                npx < xm && npy < ym -> res[0]++
                npx > xm && npy < ym -> res[1]++
                npx < xm && npy > ym -> res[2]++
                npx > xm && npy > ym -> res[3]++
            }
        }
        return res.reduce { a, b -> a * b }
    }

    override fun part2(input: String): Int {
        val robots = input.extractInts().chunked(4)

        val rows = ArrayList<Int>()
        val cols = ArrayList<Int>()

        for (time in 0..103) {
            val xs = IntArray(101)
            val ys = IntArray(103)

            for ((px, py, vx, vy) in robots) {
                val x = (px + time * vx).mod(101)
                val y = (py + time * vy).mod(103)
                xs[x]++
                ys[y]++
            }

            if (xs.count { it >= 33 } >= 2 && time < 101) cols.add(time)
            if (ys.count { it >= 31 } >= 2) rows.add(time)
        }

        check(rows.size == 1 && cols.size == 1)
        val t = cols[0]
        val u = rows[0]
        return (5253 * t + 5151 * u).mod(10403)
    }
}

fun main() = Day14().run {
    println(part1(input))
    println(part2(input))
}