package util.thread

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask

/**
 * Executes the given [action] function in parallel on chunks of this list.
 *
 * This function blocks until all parallel operations are completed.
 *
 * @param action the operation to be performed to chunks of this list
 *
 * @return a list comprising the transformed values
 */
fun <T, R> List<T>.inParallel(action: (List<T>) -> R): List<R> {
    val pool = ForkJoinPool.commonPool()
    val threads = minOf(pool.parallelism, size)
    val chunkSize = (size + threads - 1) / threads
    val tasks = ArrayList<ForkJoinTask<R>>(threads)
    for (t in 0..<threads) {
        val start = t * chunkSize
        val end = minOf(start + chunkSize, size)
        tasks += pool.submit<R> { action(this.subList(start, end)) }
    }
    return tasks.map { it.get() }
}
