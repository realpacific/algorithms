def convert_to_closest_value_divisible_by_5(num):
    """
    :param num: input
    :return: either ?5 or ?0 for two-digit number
    """
    last_digit = int(num % 10)

    base = num - last_digit

    if last_digit >= 5:
        base += 5
    return base


def find_factorial_zeros(num):
    count = 0
    base = convert_to_closest_value_divisible_by_5(num)
    if num == 0:
        return 1
    while base > 0:
        current = base
        # while it is divisible by 5, increase count by 1
        while current % 5 == 0 and current > 0:
            count += 1
            current /= 5
        base -= 5

    return count


if __name__ == '__main__':
    assert convert_to_closest_value_divisible_by_5(18) == 15
    assert convert_to_closest_value_divisible_by_5(20) == 20
    assert convert_to_closest_value_divisible_by_5(13) == 10
    assert convert_to_closest_value_divisible_by_5(10) == 10

    assert find_factorial_zeros(20) == 4
    assert find_factorial_zeros(19) == 3
    assert find_factorial_zeros(10) == 2
    assert find_factorial_zeros(5) == 1
    assert find_factorial_zeros(0) == 1
    assert find_factorial_zeros(1) == 0
