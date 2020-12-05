from collections import Counter


def equal_number_of_letters_and_numbers(string: str):
    """
    Given an array filled with letters and numbers, fnd the longest subarray with
    an equal number of letters and numbers
    :param string: string
    :return: subarray
    """
    number_count = []
    letter_count = []

    # count the number of letters and numbers
    for i in string:
        if i.isdigit():
            number_count.append(1 if len(number_count) == 0 else number_count[-1] + 1)
            letter_count.append(0 if len(letter_count) == 0 else letter_count[-1])
        else:
            letter_count.append(1 if len(letter_count) == 0 else letter_count[-1] + 1)
            number_count.append(0 if len(number_count) == 0 else number_count[-1])

    diff = []

    # Calculate the difference in each position
    for i in range(0, len(number_count)):
        diff.append(letter_count[i] - number_count[i])

    print(' '.join(map(lambda x: str(x).center(3), list(range(0, len(diff))))))
    print(' '.join(map(lambda x: str(x).center(3), list(string))))
    print(' '.join(map(lambda x: str(x).center(3), letter_count)))
    print(' '.join(map(lambda x: str(x).center(3), number_count)))
    print(' '.join(map(lambda x: str(x).center(3), diff)))

    # the result is the furthest value of same difference
    counter = Counter(diff)
    largest_diff_range = (None, None)
    largest_index = (None, None)
    for (value, counts) in counter.most_common():
        if counts <= 1:
            continue
        first_index = diff.index(value)
        last_index = len(diff) - 1 - list(reversed(diff)).index(value)
        difference = last_index - first_index

        if largest_diff_range[0] is None or difference > largest_diff_range[0]:
            largest_diff_range = (difference, value)
            largest_index = (first_index, last_index)

    # first substring starts from +1 of the range
    result = string[largest_index[0] + 1: largest_index[1] + 1]
    print(result)
    return result


if __name__ == "__main__":
    equal_number_of_letters_and_numbers("aaaa11a11aa1aa1aaaaa")
