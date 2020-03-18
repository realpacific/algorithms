def multiply(num1: str, num2: str):
    sum = 0
    lenght1 = len(num1)
    lenght2 = len(num2)

    for i in range(lenght2 - 1, -1, -1):
        session_res = 0
        session_sum = 0
        carry = 0
        for j in range(lenght1 - 1, -1, -1):
            mul = (int(num1[j]) * int(num2[i])) + carry
            # Do not calculate carry when j == 0 eg 36*4
            if j > 0:
                rem = mul % 10
                carry = int(mul / 10)
            else:
                rem = mul
                carry = 0
            # Multiply remainder based on its position i.e ones, tens, hundred ...
            session_sum += rem * (10 ** ((lenght1 - 1) - j))

        # Append 0's to last of the result of multiplication
        session_res = session_sum * (10 ** (lenght2 - 1 - i))
        print(session_res)
        # Use the remainder carry
        if carry != 0:
            session_res = (carry * 10 ** (len(str(session_res)))) + session_res
        print(session_res, carry)
        sum += session_res
    #
    # print(num1, "*", num2, "=", sum)
    # print("---------------------------")
    return sum


def test(num1, num2):
    assert multiply(num1, num2) == int(num1) * int(num2)


test("12", "34")
test("123", "434")
test("120", "340")
test("1234", "67890")
test("56789", "0")
test("1", "2333333")
test("0", "0")
test("0123", "123123")
multiply("34567890", "987653")
multiply("36", "3")
