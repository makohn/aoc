package util.point

import kotlin.math.abs

val UP = Point(-1, 0)
val RIGHT = Point(0, 1)
val DOWN = Point(1, 0)
val LEFT = Point(0, -1)

val RDLU = arrayOf(RIGHT, DOWN, LEFT, UP)

/**
 * Represents a coordinate in a 2D grid.
 *
 * @param i the row
 * @param j the column
 */
class Point(i: Int, j: Int) {

    var i: Int = i; private set
    var j: Int = j; private set

    operator fun plus(other: Point) = Point(this.i + other.i, this.j + other.j)

    operator fun plusAssign(other: Point) {
        this.i += other.i
        this.j += other.j
    }

    operator fun minus(other: Point) = Point(this.i - other.i, this.j - other.j)

    operator fun minusAssign(other: Point) {
        this.i -= other.i
        this.j -= other.j
    }

    operator fun times(rhs: Int) = Point(this.i * rhs, this.j * rhs)

    /**
     * Returns the Manhattan distance to the other point.
     */
    fun distanceTo(other: Point) = abs(this.i - other.i) + abs(this.j - other.j)

    operator fun component1() = this.i
    operator fun component2() = this.j

    override fun equals(other: Any?) = other is Point && this.i == other.i && this.j == other.j
    override fun hashCode() = 31 * i * j
    override fun toString() = "($i, $j)"
}
