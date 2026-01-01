package util.algorithm

import java.util.PriorityQueue
import kotlin.collections.plusAssign

/**
 * Performs a breadth-first search.
 *
 * @param start the node where to start
 * @param adjacent a function providing nodes adjacent to a given node
 * @param N the type of the node
 *
 * @return a map providing the distance to each node
 */
fun <N> bfs(start: N, adjacent: (N) -> List<N>): HashMap<N, Int> {

    val queue = ArrayDeque<N>()
    val visited = HashMap<N, Int>()

    fun enqueue(node: N, distance: Int) {
        if (node in visited) return
        visited[node] = distance
        queue += node
    }

    enqueue(start, 0)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        val distance = visited[node]!! + 1

        for (otherNode in adjacent(node)) {
            enqueue(otherNode, distance)
        }
    }

    return visited
}

/**
 * Performs Dijkstra's algorithm for finding the shortest path.
 *
 * @param start the node where to start
 * @param adjacent a function providing nodes adjacent to a given node with their corresponding weight
 * @param N the type of the node
 *
 * @return a map providing the distance to each node
 */
fun <N> dijkstra(start: N, adjacent: (N) -> List<Pair<N, Int>>): HashMap<N, Int> {

    val queue = PriorityQueue<Pair<Int, N>>(compareBy{ it.first })
    val visited = HashMap<N, Int>()

    fun enqueue(node: N, distance: Int) {
        if (node !in visited || distance < visited[node]!!) {
            visited[node] = distance
            queue += distance to node
        }
    }

    enqueue(start, 0)

    while (queue.isNotEmpty()) {
        val (cost, node) = queue.remove()
        if (cost <= visited[node]!!) {
            for ((otherNode, weight) in adjacent(node)) {
                val distance = cost + weight
                enqueue(otherNode, distance)
            }
        }
    }
    return visited
}