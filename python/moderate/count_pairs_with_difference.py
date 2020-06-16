import timeit


def count_pairs_with_difference(k, a):
    a_sort = sorted(a)
    count = 0
    x = timeit.timeit()
    length = len(a_sort)
    for i in range(0, length):
        required = i + k
        try:
            first_index = a.index(required)
            count += 1
            for item in range(first_index + 1, length):
                if item == required:
                    count += 1
                else:
                    continue
        except ValueError:
            continue

    print(count, x - timeit.timeit())
    return count


if __name__ == '__main__':
    count_pairs_with_difference(3, [1, 6, 8, 2, 4, 9, 12])
