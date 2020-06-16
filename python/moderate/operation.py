def negate(a):
    if a == 0:
        return a
    result = 0
    counter = 0
    while counter != a:
        result = result + (-1 if a > 0 else 1)
        counter = counter + (-1 if a < 0 else 1)
    return result


def subtraction(a, b):
    return a + negate(b)


def multiply(a, b):
    counter = 1
    result = 0

    multiplier = a if a <= b else b
    multiplee = a if a >= b else b

    # the final result needs to be negated only if either a or b is -ve
    needs_to_negate = False

    # remove -ve value
    # if both are negative, then result will be +ve
    if multiplee < 0 and multiplier < 0:
        multiplee = negate(multiplee)
        multiplier = negate(multiplier)
        needs_to_negate = False
    elif multiplier < 0 or multiplee < 0:
        needs_to_negate = True
        if multiplier < 0:
            multiplier = negate(multiplier)
        else:
            multiplee = negate(multiplee)

    # add multiplee to itself multiplier times
    while counter <= multiplier:
        result += multiplee
        counter += 1

    # negate result if needed
    return negate(result) if needs_to_negate else result


if __name__ == '__main__':
    assert negate(10) == -10
    assert negate(-10) == 10
    assert negate(0) == 0

    assert subtraction(10, 20) == -10
    assert subtraction(1, 0) == 1
    assert subtraction(0, 1) == -1
    assert subtraction(100, 50) == 50

    assert multiply(1, 100) == 100
    assert multiply(10, 10) == 100
    assert multiply(-1, -20) == 20
    assert multiply(-10, 20) == -200
    assert multiply(10, -20) == -200
