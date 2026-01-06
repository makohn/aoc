package util.iter

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

/**
 * Transposes a list of equal-sized lists by swapping its rows with its columns.
 *
 * For example, a 2x3 list
 * ```
 * [a, b, c],
 * [1, 2, 3]
 * ```
 * will be transposed into the 3x2 list
 * ```
 * [a, 1],
 * [b, 2],
 * [c, 3]
 * ```
 *
 * @param E the type of elements contained in the list.
 * @return a list of the original list's columns
 */
fun <E> List<List<E>>.transpose() = List(this[0].size) { j -> List(size) { i -> this[i][j] } }

/**
 * Replaces the first element of this list matching the given [predicate].
 */
fun <E> MutableList<E>.replaceFirst(element: E, predicate: (E) -> Boolean) {
    val index = indexOfFirst(predicate)
    if (index != -1) this[index] = element
}