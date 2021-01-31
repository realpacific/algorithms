def zero_matrix(matrix):
    position_of_zeros = []
    row = len(matrix)
    col = len(matrix[0])
    for i in range(0, row):
        for j in range(0, col):
            if matrix[i][j] == 0:
                position_of_zeros.append((i, j))

    for (i, j) in position_of_zeros:
        for k in range(0, col):
            matrix[i][k] = 0
        for k in range(0, row):
            matrix[k][j] = 0

    return matrix


if __name__ == "__main__":
    print(zero_matrix([[0, 2, 3, 4], [5, 6, 7, 8],
                       [9, 10, 0, 12], [13, 14, 15, 16]]))

    print(zero_matrix([[0, 2, 3, 4], [5, 6, 7, 8], [
        9, 10, 0, 12], [13, 14, 15, 16], [1, 2, 3, 0]]))
