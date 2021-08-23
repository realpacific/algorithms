package arraysandstrings


// TODO incomplete
/**
 * @suppress
 */
fun main() {
    val length = 4
    // [[0, 1, 2, 3], [4, 5, 6, 7], [8, 9, 10, 11], [12, 13, 14, 15]]
    val matrix = MutableList(length) { row ->
        MutableList(length) { col ->
            ((length * row) + col)
        }
    }
    println(matrix)

    // [[0, 1, 2, 3], [3, 7, 11, 15], [12, 13, 14, 15], [0, 4, 8, 12], [5, 6], [6, 10], [9, 10], [5, 9]]
    val holder = mutableListOf<MutableList<Int>>()
    for (layer in 0 until (length) / 2) {
        val layerComplement = length - layer - 1
        // there are 4 sides in a matrix
        for (side in 0..3) {
            val list = mutableListOf<Int>()
            for (i in layer..layerComplement) {
                when (side) {
                    0 -> {
                        list.add(matrix[layer][i])
                    }
                    1 -> {
                        list.add(matrix[i][layerComplement])
                    }
                    2 -> {
                        list.add(matrix[layerComplement][i])
                    }
                    3 -> {
                        list.add(matrix[i][layer])
                    }
                }
            }
            holder.add(list)
        }
    }
    println(holder)

    for (i in 0..holder.lastIndex) {
        val side = i % 4
        when (side) {
            0 -> {

            }
        }
    }
}

fun setColumnOfMatrix(colIndex: Int, rowStart: Int, rowEnd: Int, matrix: MutableList<MutableList<Int>>, updateList: List<Int>) {
    val length = matrix.lastIndex
    for (i in rowStart..rowEnd) {
        matrix[i][colIndex] = updateList[i]
    }
}