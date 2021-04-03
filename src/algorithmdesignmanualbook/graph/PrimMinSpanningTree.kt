package algorithmdesignmanualbook.graph

/**
 * Solution: https://www.programiz.com/dsa/prim-algorithm
 *
 * Given vertices with weighted edges, from start vertex, chose the edge with min edge such that it doesnt form cycle.
 */
fun prim(graph: Array<Array<Int>>, vertex: Int) {
    var sum = 0
    var numberOfEdges = 0
    val selected = Array(vertex) { false }
    // Start from the root
    selected[0] = true

    // Number of edge is always less than V-1 and no cycles
    while (numberOfEdges < vertex - 1) {
        var min = Integer.MAX_VALUE
        var x = 0 // row
        var y = 0 // col

        for (i in 0 until vertex) {
            // min cost edge is always selected
            if (selected[i]) {
                for (j in 0 until vertex) {
                    if (!selected[j] && graph[i][j] != 0 && min > graph[i][j]) {
                        // if min, record it
                        min = graph[i][j]
                        x = i
                        y = j
                    }
                }
            }
        }
        println("$x $y : ${graph[x][y]}")
        sum += graph[x][y]
        numberOfEdges++
        // select the min cost edge
        selected[y] = true
    }
    println("Sum = $sum")
}


fun main() {
    // Adjacent matrix
    val matrix = arrayOf(
            arrayOf(0, 9, 75, 0, 0),
            arrayOf(9, 0, 95, 19, 42),
            arrayOf(75, 95, 0, 51, 66),
            arrayOf(0, 19, 51, 0, 31),
            arrayOf(0, 42, 66, 31, 0)
    )
    prim(matrix, matrix.size)
}