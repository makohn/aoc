package aoc.math

//region Number-theoretic algorithms
/**
 * Returns the greatest common divisor between two numbers.
 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

/**
 * Returns the least common multiple between two numbers.
 */
fun lcm(a: Long, b: Long) = a / gcd(a, b) * b

/**
 * Returns the least common multiple of all elements in the collection.
 */
fun Iterable<Long>.lcm() = reduce { acc, i -> lcm(acc, i) }

/**
 * Returns the product of all elements in the collection.
 */
fun Iterable<Int>.product() = reduce { a, b -> a * b }
//endregion

//region Matrix definitions
/**
 * Represents a `m x n` matrix of double precision values.
 *
 * @param m the number of rows in this matrix
 * @param n the number of columns in this matrix
 * @param data the entries of this matrix
 */
open class Matrix(
    val m: Int,
    val n: Int,
    val data: DoubleArray = DoubleArray(m * n) { 0.0 }
) {

    /**
     * Returns the matrix product between this matrix and the other matrix.
     */
    operator fun times(other: Matrix): Matrix {
        check(this.n == other.m)

        val size = this.m * other.n
        val res = DoubleArray(size)

        for (i in 0..<this.m) for (j in 0..<other.n) {
            var sum = 0.0
            for (k in 0..<this.n) sum += this.data[i * this.n + k] * other.data[k * other.n + j]
            res[i * other.n + j] = sum
        }
        return Matrix(this.m, other.n, res)
    }

    /**
     * Returns the string representation of this matrix.
     */
    override fun toString() = buildString {
        for (i in 0..<m) {
            for (j in 0..<n) append(data[(i * n) + j]).append(" ")
            append("\n")
        }
    }

    /**
     * Checks if this matrix is equal to the other matrix by comparing their contents.
     */
    override fun equals(other: Any?) = (other is Matrix) && this.data.contentEquals(other.data)

    /**
     * Returns a hash code based on the contents of this matrix
     */
    override fun hashCode() = data.contentHashCode()
}

/**
 * Represents a `4 x 4` matrix of double precision values.
 *
 * @param data the entries of this matrix
 */
class Matrix4x4(data: DoubleArray = DoubleArray(16) { 0.0 }) : Matrix(4, 4, data) {

    /**
     * Returns the inverse of this matrix.
     */
    fun inverse(): Matrix4x4 {
        fun valueAt(ii: Int, jj: Int): Double {
            val o = 2 + (jj - ii)
            val i = ii + 4 + o
            val j = jj + 4 - o

            fun e(a: Int, b: Int) = data[((j + b) % 4) * 4 + ((i + a) % 4)]

            val inv = e(+1, -1) * e(+0, +0) * e(-1, +1) +
                    e(+1, +1) * e(+0, -1) * e(-1, +0) +
                    e(-1, -1) * e(+1, +0) * e(+0, +1) -
                    e(-1, -1) * e(+0, +0) * e(+1, +1) -
                    e(-1, +1) * e(+0, -1) * e(+1, +0) -
                    e(+1, -1) * e(-1, +0) * e(+0, +1)

            return if (o % 2 == 0) inv else -inv
        }

        val inv = DoubleArray(16)
        for (i in 0..<4) for (j in 0..<4) inv[j * 4 + i] = valueAt(i, j)

        var d = 0.0
        for (k in 0..<4) d += data[k] * inv[k * 4]

        d = 1.0 / d
        for (i in 0..<16) inv[i] *= d

        return Matrix4x4(inv)
    }
}
//endregion