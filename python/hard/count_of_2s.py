def count_of_2_at_digit(number: int, digit: int) -> int:
    power_of_10 = 10 ** digit
    next_power_of_10 = power_of_10 * 10

    round_down = number - (number % next_power_of_10)
    round_up = round_down + next_power_of_10

    digit = (int(number / power_of_10)) % 10

    if digit > 2:
        return int(round_up / 10)
    elif digit < 2:
        return int(round_down / 10)
    else:
        right_part = number % power_of_10
        return int(round_down / 10) + right_part + 1


def find_digit_count(num: int) -> int:
    _number = num
    count = 0
    while int(_number / 10) > 0:
        count += 1
        _number = _number / 10
    return count + 1


def count_of_2s(number: int) -> int:
    """
    Find the number of 2s in between 0 and n
    :param number: the max value (inclusive)
    :return: Number of 2s
    """
    digit_length = find_digit_count(number)
    result = 0
    for i in range(0, digit_length):
        result += count_of_2_at_digit(number, digit=i)
    print(f"# of 2s in {number} = ", result)
    return result


if __name__ == "__main__":
    assert count_of_2s(120) == 23
    # In between 20..29 there are 11 2s as 22 exits
    assert count_of_2s(30) == 13
    assert count_of_2s(50) == 15
