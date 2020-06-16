SHORTER_LENGTH = 10
LONG_LENGTH = 18


def diving_board(k: int, result: set, memo: set, total_length: int = 0, ):
    if k == 0:
        result.add(total_length)
        return result

    # this path has already been visited
    key = str(k) + "_" + str(total_length)
    if key in memo:
        return
    else:
        memo.add(key)

    diving_board(k - 1, total_length=SHORTER_LENGTH + total_length, result=result, memo=memo)
    diving_board(k - 1, total_length=LONG_LENGTH + total_length, result=result, memo=memo)


def optimal(k: int) -> set:
    """
    Arranging k planks end-to-end means that arranging (k short, 0 long), (k-1 short, 1 long), (k-2 short, 2 long)...
    :param k: the number of planks
    :return: the all possible length of arrangements
    """
    result = set()
    for i in range(0, k + 1):
        length = SHORTER_LENGTH * i + LONG_LENGTH * (k - i)
        result.add(length)
    return result


if __name__ == '__main__':
    res = set()
    visited_records = set()
    diving_board(k=100, result=res, memo=visited_records)

    print('-----')
    optimal_res = optimal(k=100)

    print(res - optimal_res)
    print(res)
    assert len(res) == len(optimal_res)
