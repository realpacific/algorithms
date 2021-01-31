def quick_sort(arr, low, high):
    if low == high:
        return
    max = partition(arr, low, high)
    quick_sort(arr, low, max)
    quick_sort(arr, max + 1, high)


def partition(A, low, high):
    pivot = A[low]
    i = low
    j = high
    while i < j:
        while True:
            i += 1
            print('inc i ', i, A[i])
            if i >= len(A) or A[i] > pivot:
                break

        while True:
            j -= 1
            print('dec j ', j, A[j])
            if j < 0 or A[j] <= pivot:
                break

        if i < j:
            A[i], A[j] = A[j], A[i]
            print('swaping', A[i], A[j], '--->', A)

    A[low], A[j] = A[j], A[low]
    print('swapping pivot', A)
    return j


if __name__ == "__main__":
    # array = [9, 8, 1, 10, 20, 0]
    array = [10, 16, 8, 12, 15, 6, 3, 9, 5]
    print(array)
    array.append(100000)
    quick_sort(array, 0, len(array) - 1)
    print(array[0:-1])
