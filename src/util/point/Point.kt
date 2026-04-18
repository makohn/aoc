package util.point

import kotlin.math.abs

val ORIGIN = Point(0, 0)

val UP = Point(0, -1)
val RIGHT = Point(1, 0)
val DOWN = Point(0, 1)
val LEFT = Point(-1, 0)

val RDLU = arrayOf(RIGHT, DOWN, LEFT, UP)

/**
 * Represents a coordinate in a 2D grid.
 *
 * @param x the column
 * @param y the row
 */
class Point(x: Int, y: Int) {

    var x: Int = x
        private set
    var y: Int = y
        private set

    operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)

    operator fun plusAssign(other: Point) {
        this.y += other.y
        this.x += other.x
    }

    operator fun minus(other: Point) = Point(this.x - other.x, this.y - other.y)

    operator fun minusAssign(other: Point) {
        this.y -= other.y
        this.x -= other.x
    }

    operator fun times(rhs: Int) = Point(this.x * rhs, this.y * rhs)

    /**
     * Returns the Manhattan distance to the other point.
     */
    fun distanceTo(other: Point) = abs(this.y - other.y) + abs(this.x - other.x)

    /**
     * Rotates in clockwise direction.
     */
    fun clockwise() = Point(this.y, -this.x)

    /**
     * Rotates in counterclockwise direction.
     */
    fun counterClockwise() = Point(-this.y, this.x)

    operator fun component1() = this.x
    operator fun component2() = this.y

    override fun equals(other: Any?) = other is Point && this.y == other.y && this.x == other.x
    override fun hashCode() = 31 * y + x
    override fun toString() = "($x, $y)"
}
