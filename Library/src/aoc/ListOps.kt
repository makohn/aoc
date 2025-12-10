package aoc

/**
 * Returns a sequence of all possible combinations of elements from this list with length `r`.
 *
 * For example
 * ```
 * listOf("a", "b", "c", "d").combineElements(3).toList()
 * ```
 * provides the following sequence:
 * ```
 * [[a, b, c], [a, b, d], [a, c, d], [b, c, d]]
 * ```
 *
 * @param E the type of elements contained in the list.
 * @param r the number (`>= 0`) of elements per combination
 * @return a sequence of lists with length `r` each
 */
fun <E> List<E>.combineElements(r: Int): Sequence<List<E>> {
    require(r >= 0) { "r must be >= 0, but was $r" }
    val pool = this
    val n = pool.size
    val indices = IntArray(r) { it }
    return sequence {
        yield(List(r) { pool[indices[it]] })
        while (true) {
            var i = r - 1
            while (i >= 0) {
                if (indices[i] != i + n - r) break
                i--
            }
            if (i < 0) break
            indices[i] += 1
            for (j in i + 1..<r) {
                indices[j] = indices[j - 1] + 1
            }
            yield(List(r) { pool[indices[it]] })
        }
    }
}