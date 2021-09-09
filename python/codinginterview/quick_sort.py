def quick_sort(arr, low, high):
    if low == high:
        return
    max_ = partition(arr, low, high)
    quick_sort(arr, low, max_)
    quick_sort(arr, max_ + 1, high)


def partition(arr, low, high):
    pivot = arr[low]
    i = low
    j = high
    while i <= j:
        while True:
            i += 1
            if i >= len(arr) or arr[i] > pivot:
                break

        while True:
            j -= 1
            if j < 0 or arr[j] <= pivot:
                break

        if i < j:
            arr[i], arr[j] = arr[j], arr[i]

    arr[low], arr[j] = arr[j], arr[low]
    return j


def test_1():
    array = [1000, 10, 16, 8, 12, 15, 6, 3, 9, 5, 1000000]
    expected = sorted(array)
    quick_sort(array, 0, len(array))
    assert array == expected


def test_2():
    array = [1000, 5, 100, 8, 0, 1000000, 9]
    expected = sorted(array)
    quick_sort(array, 0, len(array))
    assert array == expected


def test_3():
    array = [1000, 5, 100, 8, 0, 1, 4, 9]
    expected = sorted(array)
    quick_sort(array, 0, len(array))
    assert array == expected


def test_4():
    array = [90, 5, 100]
    expected = sorted(array)
    quick_sort(array, 0, len(array))
    assert array == expected


def test_5():
    array = [90, 100]
    expected = sorted(array)
    quick_sort(array, 0, len(array))
    assert array == expected


if __name__ == "__main__":
    test_1()
    test_2()
    test_3()
    test_4()
    test_5()
