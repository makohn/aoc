import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("2024/src/$name.txt").readText().trim().lines()

/**
 * Reads a string from the given input txt file.
 */
fun readInputString(name: String) = Path("2024/src/$name.txt").readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

typealias CharMatrix = Array<CharArray>
fun List<String>.toCharMatrix() = Array(size) { idx -> get(idx).toCharArray() }
val CharMatrix.dimension get() = size to get(0).size
