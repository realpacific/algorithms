from typing import List


def _find_ponds(mat: List[List[int]], row: int, col: int, traversed_data: List[str]) -> int:
    if row < 0 or row >= len(mat) or col < 0 or col >= len(mat[0]):
        return 0
    if mat[row][col] != 0 or (str(row) + str(col) in traversed_data):
        return 0
    number_of_ponds = 1
    traversed_data.append(str(row) + str(col))
    # recursively iterate to surrounding
    number_of_ponds += _find_ponds(mat, row, col - 1, traversed_data)
    number_of_ponds += _find_ponds(mat, row, col + 1, traversed_data)
    number_of_ponds += _find_ponds(mat, row + 1, col - 1, traversed_data)
    number_of_ponds += _find_ponds(mat, row + 1, col, traversed_data)
    number_of_ponds += _find_ponds(mat, row + 1, col + 1, traversed_data)
    return number_of_ponds


def pond_sizes(mat: List[List[int]]) -> List[int]:
    """
     You have an integer matrix representing a plot of land, where the value at that location
    represents the height above sea level. A value of zero indicates water. A pond is a region of water
    connected vertically, horizontally, or diagonally. The size of the pond is the total number of
    connected water cells. Write a method to compute the sizes of all ponds in the matrix
    """
    result = []
    traversed = []
    for i in range(0, len(mat)):
        for j in range(0, len(mat[0])):
            ponds = _find_ponds(mat, i, j, traversed)
            if ponds > 0:
                result.append(ponds)
    print(result)
    return sorted(result)


if __name__ == '__main__':
    assert pond_sizes([
        [1, 2, 1, 0],
        [1, 1, 0, 1],
        [1, 1, 0, 1],
        [0, 0, 1, 1]
    ]) == [5]

    assert pond_sizes([
        [0, 0, 0, 0],
        [1, 1, 1, 1],
        [1, 1, 1, 1],
        [0, 1, 1, 0]
    ]) == [1, 1, 4]

    assert pond_sizes([
        [1, 2, 1, 0],
        [1, 1, 1, 1],
        [1, 1, 1, 1],
        [0, 0, 0, 0]
    ]) == [1, 4]

    assert pond_sizes([
        [0, 2, 1, 0],
        [0, 1, 0, 1],
        [0, 1, 0, 1],
        [0, 1, 1, 0]
    ]) == [4, 4]

    assert pond_sizes([
        [0, 2, 1, 1],
        [0, 1, 0, 1],
        [0, 1, 0, 1],
        [0, 1, 0, 1]
    ]) == [3, 4]

    assert pond_sizes([
        [0, 2, 1, 1],
        [0, 0, 0, 1],
        [0, 1, 0, 1],
        [0, 1, 0, 1]
    ]) == [8]

    assert pond_sizes([
        [1, 2, 1, 1],
        [1, 1, 1, 1],
        [1, 1, 1, 1],
        [1, 1, 1, 1]
    ]) == []

    assert pond_sizes([
        [1, 2, 1, 0],
        [1, 1, 0, 1],
        [1, 1, 0, 1],
        [1, 0, 1, 1]
    ]) == [4]
