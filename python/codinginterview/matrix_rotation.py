def rotate_matrix(matrix):
    """
    https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
    """
    n = len(matrix[0])
    for x in range(0, int(n / 2)):
        for y in range(x, n - 1 - x):
            temp = matrix[x][y]
            matrix[x][y] = matrix[y][n - 1 - x]
            matrix[y][n - 1 - x] = matrix[n - 1 - x][n - 1 - y]
            matrix[n - 1 - x][n - 1 - y] = matrix[n - 1 - y][x]
            matrix[n - 1 - y][x] = temp
    return matrix


if __name__ == "__main__":
    assert rotate_matrix([
        [1, 2, 3, 4],
        [5, 6, 7, 8],
        [9, 10, 11, 12],
        [13, 14, 15, 16]]
    ) == [[4, 8, 12, 16],
          [3, 7, 11, 15],
          [2, 6, 10, 14],
          [1, 5, 9, 13]]

    assert rotate_matrix([
        [1, 2, 3, 4, 5],
        [6, 7, 8, 9, 10],
        [11, 12, 13, 14, 15],
        [16, 17, 18, 19, 20],
        [21, 22, 23, 24, 25]]
    ) == [[5, 10, 15, 20, 25],
          [4, 9, 14, 19, 24],
          [3, 8, 13, 18, 23],
          [2, 7, 12, 17, 22],
          [1, 6, 11, 16, 21]]
