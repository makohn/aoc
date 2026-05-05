package util.grid

import util.point.*

/**
 * A 2D grid of chars.
 */
typealias CharGrid = Array<CharArray>

/**
 * A 2D grid of booleans.
 */
typealias BooleanGrid = Array<BooleanArray>

/**
 * A 2D grid of integers.
 */
typealias IntGrid = Array<IntArray>

/**
 * A generic 2D grid.
 */
typealias Grid<T> = Array<Array<T>>

/**
 * Returns the shape (width, height) of this 2D grid.
 */
val CharGrid.shape get() = Point(get(0).size, size)

/**
 * Returns the shape (width, height) of this 2D grid.
 */
val IntGrid.shape get() = Point(get(0).size, size)

/**
 * Shortcut for ```grid[point.y][point.x]```
 */
operator fun CharGrid.get(point: Point): Char = this[point.y][point.x]

/**
 * Shortcut for ```grid[point.y][point.x]```
 */
operator fun BooleanGrid.get(point: Point): Boolean = this[point.y][point.x]

/**
 * Shortcut for ```grid[point.y][point.x]```
 */
operator fun IntGrid.get(point: Point): Int = this[point.y][point.x]

/**
 * Shortcut for ```grid[point.y][point.x]```
 */
operator fun <T> Grid<T>.get(point: Point): T = this[point.y][point.x]

/**
 * Shortcut for ```grid[point.y][point.x] = value```
 */
operator fun CharGrid.set(point: Point, value: Char) {
    this[point.y][point.x] = value
}

/**
 * Shortcut for ```grid[point.y][point.x] = value```
 */
operator fun BooleanGrid.set(point: Point, value: Boolean) {
    this[point.y][point.x] = value
}

/**
 * Shortcut for ```grid[point.y][point.x] = value```
 */
operator fun IntGrid.set(point: Point, value: Int) {
    this[point.y][point.x] = value
}

/**
 * Shortcut for ```grid[point.y][point.x] = value```
 */
operator fun <T> Grid<T>.set(point: Point, value: T) {
    this[point.y][point.x] = value
}

/**
 * Checks whether the point is within the grid's bounds
 */
operator fun CharGrid.contains(point: Point): Boolean = point.y in 0..<size && point.x in 0..<get(0).size

/**
 * Checks whether the point is within the grid's bounds
 */
operator fun IntGrid.contains(point: Point): Boolean = point.y in 0..<size && point.x in 0..<get(0).size

/**
 * Returns the position of the first occurrence of the specified element in the grid.
 */
fun CharGrid.positionOf(element: Char): Point {
    for (y in 0..<size) {
        for (x in 0..<get(0).size) {
            if (this[y][x] == element) {
                return Point(x, y)
            }
        }
    }
    error(element)
}

/**
 * Returns the position of the first occurrence of the specified element in the grid.
 */
fun IntGrid.positionOf(element: Int): Point {
    for (y in 0..<size) {
        for (x in 0..<get(0).size) {
            if (this[y][x] == element) {
                return Point(x, y)
            }
        }
    }
    error(element)
}

/**
 * Returns a 2D [CharGrid] containing the strings of this list.
 */
fun List<String>.toCharGrid(): CharGrid = Array(size) { get(it).toCharArray() }

/**
 * Returns a 2D [IntGrid] containing the digits of this list's rows.
 */
fun List<String>.toIntGrid(): IntGrid = Array(size) { row -> get(row).map { it.digitToInt() }.toIntArray() }

/**
 * Constructs a boolean grid with the given shape (rows, columns)
 */
fun BooleanGrid(shape: Point): BooleanGrid = Array(shape.y) { BooleanArray(shape.x) }

/**
 * Constructs an [IntGrid] of size ([m], [n]) (rows, columns) and initializes with the [init] function.
 */
fun IntGrid(m: Int, n: Int, init: (Int) -> Int): IntGrid = Array(m) { IntArray(n, init) }

/**
 * Constructs an [IntGrid] with the given shape (rows, columns) and initializes with the [init] function.
 */
fun IntGrid(shape: Point, init: (Int) -> Int): IntGrid = Array(shape.y) { IntArray(shape.x, init) }

/**
 * Constructs a generic grid with the given shape (rows, columns)
 */
inline fun <reified T> Grid(shape: Point, noinline init: (Int) -> T): Grid<T> = Array(shape.y) { Array(shape.x, init) }

/**
 * Returns a string representation of the rows of the specified 2D char grid.
 */
fun CharGrid.rowsToString(rowSeparator: String = "\n", columnSeparator: String = "") = joinToString(rowSeparator) { it.joinToString(columnSeparator) }

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
    val (width, height) = shape
    val ret = Array(width) { CharArray(height) }
    for (i in 0..<height) {
        for (j in 0..<width) {
            ret[j][i] = get(height - i - 1)[j]
        }
    }
    return ret
}

/**
 * Represents a direction in a 2D grid.
 */
enum class Direction(val xDir: Int, val yDir: Int) {
    North(0, -1),
    West(-1, 0),
    East(1, 0),
    South(0, 1),
    ;

    operator fun component1() = xDir
    operator fun component2() = yDir
}
