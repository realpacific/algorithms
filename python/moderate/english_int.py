smalls = ["Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
          "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
          "Sixteen", "Seventeen", "Eighteen", "Nineteen"]
tens = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
        "Eighty", "Ninety"]
bigs = ["", "Thousand", "Million", "Billion"]
hundred = "Hundred"


def resolve_hundreds(value):
    return value + " " + hundred + " and "


def resolve_commas_place(comma_pos):
    return bigs[comma_pos]


def convert(value, pos):
    result = ""
    trimmed_value = str(int(value))
    is_hundred = False
    if len(trimmed_value) == 3:
        result += resolve_hundreds(smalls[int(trimmed_value[0])])
        is_hundred = True

    if len(trimmed_value) == 2 or is_hundred:
        removed_hundred = trimmed_value[1:] if is_hundred else trimmed_value
        removed_hundred = str(int(removed_hundred))

        if int(removed_hundred) <= len(smalls):
            result += smalls[int(removed_hundred)] + " "
        else:
            for idx, i in enumerate(removed_hundred):
                if idx == 0:
                    result += tens[int(i)] + " "
                else:
                    result += smalls[int(i)] + " "

    if len(trimmed_value) == 1:
        result += smalls[int(trimmed_value)] + " "
    return result + resolve_commas_place(pos)


def english_int(num):
    split = reversed(num.split(','))
    result = []
    for idx, value in enumerate(split):
        result.append(convert(value, idx))
    return ' '.join(reversed(result))


if __name__ == '__main__':
    # nineteen million two hundred and 34 thousand one hundred and seventy five
    print(english_int("19,234,175"))
    print(english_int("69,019,004,175"))
    print(english_int("69,009,104,175"))
