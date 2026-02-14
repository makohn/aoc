package util.grid

import util.point.*

/**
 * A 2D grid of chars.
 */
typealias CharGrid = Array<CharArray>

/**
 * Returns the shape `(m, n)` (rows, columns) of this 2D grid.
 */
val CharGrid.shape get() = Point(size, get(0).size)

/**
 * Returns a 2D [CharGrid] containing the strings of this list.
 */
fun List<String>.toCharGrid(): CharGrid = Array(size) { get(it).toCharArray() }

/**
 * Returns a string representation of the rows of the specified 2D char grid.
 */
fun CharGrid.rowsToString(rowSeparator: String = "\n", columnSeparator: String = "") =
    joinToString(rowSeparator) { it.joinToString(columnSeparator) }

/**
 * Shortcut for ```grid[point.i][point.j]```
 */
operator fun CharGrid.get(point: Point): Char = this[point.i][point.j]

/**
 * Shortcut for ```grid[point.i][point.j] = value```
 */
operator fun CharGrid.set(point: Point, value: Char) {
    this[point.i][point.j] = value
}

/**
 * Checks whether the point is within the grid's bounds
 */
operator fun CharGrid.contains(point: Point): Boolean = point.i in 0..<size && point.j in 0..<get(0).size

/**
 * Returns a 2D char grid of all elements rotated in clockwise direction by 90 degrees.
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
fun CharGrid.rotated(): CharGrid {
    val (n, m) = shape
    val ret = Array(m) { CharArray(n) }
    for (i in 0..<n) for (j in 0..<m)
        ret[j][i] = get(n - i - 1)[j]
    return ret
}

/**
 * Returns the position of the first occurrence of the specified element in the grid, or `-1, -1` if the specified
 * element is not contained in the grid.
 */
fun CharGrid.positionOf(c: Char): Point {
    val (n, m) = shape
    for (i in 0..<n) for (j in 0..<m) if (this[i][j] == c) return Point(i, j)
    return Point(-1, -1)
}

/**
 * A 2D grid of booleans.
 */
typealias BooleanGrid = Array<BooleanArray>

/**
 * Constructs a boolean grid with the given shape (rows, columns)
 */
fun BooleanGrid(shape: Point): BooleanGrid = Array(shape.i) { BooleanArray(shape.j) }

/**
 * Shortcut for ```grid[point.i][point.j]```
 */
operator fun BooleanGrid.get(point: Point): Boolean = this[point.i][point.j]

/**
 * Shortcut for ```grid[point.i][point.j] = value```
 */
operator fun BooleanGrid.set(point: Point, value: Boolean) {
    this[point.i][point.j] = value
}

/**
 * A 2D grid of integers.
 */
typealias IntGrid = Array<IntArray>

/**
 * Returns the shape `(m, n)` (rows, columns) of this 2D grid.
 */
val IntGrid.shape get() = Point(size, get(0).size)

/**
 * Returns a 2D [IntGrid] containing the digits of this list's rows.
 */
fun List<String>.toIntGrid(): IntGrid = Array(size) { row -> get(row).map { it.digitToInt() }.toIntArray() }

/**
 * A generic 2D grid.
 */
typealias Grid<T> = Array<Array<T>>

/**
 * Constructs a generic grid with the given shape (rows, columns)
 */
inline fun <reified T> Grid(shape: Point, noinline init: (Int) -> T): Grid<T> =
    Array(shape.i) { Array(shape.j, init) }

/**
 * Shortcut for ```grid[point.i][point.j]```
 */
operator fun <T> Grid<T>.get(point: Point): T = this[point.i][point.j]

/**
 * Shortcut for ```grid[point.i][point.j] = value```
 */
operator fun <T> Grid<T>.set(point: Point, value: T) {
    this[point.i][point.j] = value
}

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
fun CharGrid.dataPoints() = flatMapIndexed { i, r -> r.mapIndexed { j, d -> DataPoint(i, j, d) } }

/**
 * Returns a list of all points adjacent to the given x, y coordinates.
 */
fun CharGrid.neighborsOf(x: Int, y: Int): List<DataPoint<Char>> {
    val arr = this
    val (n, m) = shape
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
fun CharGrid.neighborsOf(point: DataPoint<Char>, vararg dirs: Direction): List<DataPoint<Char>> {
    val arr = this
    val (x, y, _) = point
    val (n, m) = shape
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
fun CharGrid.neighborsOfUnbound(point: DataPoint<Char>, vararg dirs: Direction): List<DataPoint<Char>> {
    val arr = this
    val (x, y, _) = point
    val (n, m) = shape
    return buildList {
        for ((j, i) in dirs) {
            val dx = x + i
            val dy = y + j
            add(DataPoint(dx, dy, arr[dx.mod(n)][dy.mod(m)]))
        }
    }
}
//endregion