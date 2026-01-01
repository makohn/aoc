import kotlin.math.abs

//region List Extensions
/**
 * Returns 6th *element* from the list.
 *
 * Throws an [IndexOutOfBoundsException] if the size of this list is less than 5.
 */
operator fun <T> List<T>.component6() = get(5)
//endregion

//region Vector Types
/**
 * Represents a 2D vector of 32-bit signed integers.
 */
data class Int2(val x: Int, val y: Int) {

    /**
     * Adds the other vector to this vector.
     */
    operator fun plus(other: Int2) = Int2(this.x + other.x, this.y + other.y)

    /**
     * Returns the Manhattan distance to the other vector.
     */
    infix fun distanceTo(other: Int2) = abs(this.x - other.x) + abs(this.y - other.y)
}

/**
 * Represents a 4D vector of 32-bit signed integers.
 */
data class Int4(val x: Int, val y: Int, val z: Int, val w: Int)
//endregion

//region CharArray2
/**
 * A 2D array of chars.
 */
typealias CharArray2 = Array<CharArray>

/**
 * Returns the number of rows and columns of this 2D array.
 */
val CharArray2.size2 get() = Int2(size, get(0).size)

/**
 * Returns a 2D [CharArray2] containing the strings of this list.
 */
fun List<String>.toCharArray2(): CharArray2 = Array(size) { get(it).toCharArray() }

/**
 * Returns a string representation of the rows of the specified 2D char array.
 */
fun CharArray2.rowsToString(rowSeparator: String = "\n", columnSeparator: String = "") =
    joinToString(rowSeparator) { it.joinToString(columnSeparator) }

/**
 * Returns a 2D char array of all elements rotated in clockwise direction by 90 degrees.
 *
 * For example
 * ```
 * [A, B, C]
 * [D, E, F]
 * [G, H, I]
 * ```
 * rotated by 90 degrees in clockwise direction yields
 * ```
 * [G, D, A]
 * [H, E, B]
 * [I, F, C]
 * ```
 */
fun CharArray2.rotated(): CharArray2 {
    val (n, m) = size2
    val ret = Array(m) { CharArray(n) }
    for (i in 0..<n) for (j in 0..<m)
        ret[j][i] = get(n - i - 1)[j]
    return ret
}

/**
 * Returns the position of the first occurrence of the specified element in the grid, or `-1, -1` if the specified
 * element is not contained in the grid.
 */
fun CharArray2.positionOf(c: Char): Int2 {
    val (n, m) = size2
    for (i in 0..<n) for (j in 0..<m) if (this[i][j] == c) return Int2(i, j)
    return Int2(-1, -1)
}
//endregion

//region IntArray2
/**
 * A 2D array of integers.
 */
typealias IntArray2 = Array<IntArray>

/**
 * Returns the number of rows and columns of this 2D array.
 */
val IntArray2.size2 get() = Int2(size, get(0).size)

/**
 * Returns a 2D [IntArray2] containing the digits of this list's rows.
 */
fun List<String>.toIntArray2(): IntArray2 = Array(size) { row -> get(row).map { it.digitToInt() }.toIntArray() }
//endregion

//region DataPoint
/**
 * Represents a datum in a 2D grid with its x and y coordinates.
 */
data class DataPoint<T>(val x: Int, val y: Int, val data: T)

/**
 * Represents a char in a 2D grid with its x and y coordinates.
 */
typealias CharPoint = DataPoint<Char>

/**
 * Returns the elements of this 2D char array as a list of [DataPoint]s.
 */
fun CharArray2.dataPoints() = flatMapIndexed { i, r -> r.mapIndexed { j, d -> DataPoint(i, j, d) } }

/**
 * Returns a list of all points adjacent to the given x, y coordinates.
 */
fun CharArray2.neighborsOf(x: Int, y: Int): List<DataPoint<Char>> {
    val arr = this
    val (n, m) = size2
    return buildList {
        for (i in -1..1) for (j in -1..1) {
            val dx = x + i
            val dy = y + j
            if (dx in 0..<n && dy in 0..<m) add(DataPoint(dx, dy, arr[dx][dy]))
        }
    }
}

/**
 * Represents a direction in a 2D grid.
 */
enum class Direction(val xDir: Int, val yDir: Int) {
    North(0, -1),
    West(-1, 0),
    East(1, 0),
    South(0, 1);

    operator fun component1() = xDir
    operator fun component2() = yDir
}

/**
 * Returns a list of points adjacent to the given point in provided directions.
 */
fun CharArray2.neighborsOf(point: DataPoint<Char>, vararg dirs: Direction): List<DataPoint<Char>> {
    val arr = this
    val (x, y, _) = point
    val (n, m) = size2
    return buildList {
        for ((j, i) in dirs) {
            val dx = x + i
            val dy = y + j
            if (dx in 0..<n && dy in 0..<m) add(DataPoint(dx, dy, arr[dx][dy]))
        }
    }
}

/**
 * Returns a list of points adjacent to the given point in provided directions.
 *
 * The grid is considered to be wrapped around.
 */
fun CharArray2.neighborsOfUnbound(point: DataPoint<Char>, vararg dirs: Direction): List<DataPoint<Char>> {
    val arr = this
    val (x, y, _) = point
    val (n, m) = size2
    return buildList {
        for ((j, i) in dirs) {
            val dx = x + i
            val dy = y + j
            add(DataPoint(dx, dy, arr[dx.mod(n)][dy.mod(m)]))
        }
    }
}
//endregion