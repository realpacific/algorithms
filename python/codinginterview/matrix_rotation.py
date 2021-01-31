def rotate_matrix(matrix):
    '''https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/'''
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
    print(rotate_matrix([[1, 2, 3, 4], [5, 6, 7, 8],
                         [9, 10, 11, 12], [13, 14, 15, 16]]))

    print(rotate_matrix([[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [
        11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]]))
