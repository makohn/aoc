package util.thread

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask

/**
 * Executes the given [transform] function in parallel on chunks of this list.
 *
 * This function blocks until all parallel operations are completed.
 *
 * @param threads the number of threads to spawn. Defaults to the number of processors
 * @param transform the operation to be performed to chunks of this list
 *
 * @return a list comprising the transformed values
 */
fun <T, R> List<T>.mapParallel(
    threads: Int = Runtime.getRuntime().availableProcessors(),
    transform: (List<T>) -> R
): List<R> {
    val chunkSize = (size + threads - 1) / threads
    val pool = ForkJoinPool(threads)
    val tasks = ArrayList<ForkJoinTask<R>>(threads)
    for (t in 0..<threads) {
        tasks.add(pool.submit<R> {
            val start = t * chunkSize
            val end = minOf(start + chunkSize, size)
            transform(this.subList(start, end))
        })
    }
    return tasks.map { it.get() }
}