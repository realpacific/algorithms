def reverse_int(x):
    if x < 0:
        x = x * -1
        isNeg = True
    else:
        isNeg = False
    string = str(x)
    result = int(string[::-1]) if not isNeg else (-1 * int(string[::-1]))
    return result if (-2 ** 31 <= result <= 2 ** 31 - 1) else 0


if __name__ == "__main__":
    print(reverse_int(110))
    print(reverse_int(900))
    print(reverse_int(789090))
    print(reverse_int(-100))
    print(reverse_int(-1))
    print(reverse_int(-123))
    assert (reverse_int(1534236469) == 0)
