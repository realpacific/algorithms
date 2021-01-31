def sortArrayByParity(A):
    result = []
    even_counts = 0
    for i in A:
        result.append(i)
        if i % 2 == 0:
            print(even_counts)
            if len(result) > 1:
                result[even_counts], result[-1] = result[-1], result[even_counts]
            even_counts += 1
    return result


def sortArrayByParity2(A):
    index_of_oldest_odd = 0
    for i in range(0, len(A)):
        if A[i] % 2 == 0:
            A[index_of_oldest_odd], A[i] = A[i], A[index_of_oldest_odd]
            index_of_oldest_odd += 1

    return A


if __name__ == "__main__":
    print(sortArrayByParity2([4, 2, 3, 1]))

    print(sortArrayByParity2([4]))
    print(sortArrayByParity2([3]))
    print(sortArrayByParity2([4, 2]))
    print(sortArrayByParity2([3, 4]))
    print(sortArrayByParity2([4, 4]))
    print(sortArrayByParity2([3, 1, 2, 4, 5]))
    print(sortArrayByParity2([3, 3, 3, 3, 2, 2, 2, 2, 3, 3, 3, 1, 2, 4, 5, 5, 5, 0]))
