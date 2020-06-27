def __build_from_pattern(pattern: str, a: str, b: str):
    result = []
    starter = pattern[0]
    for i in pattern:
        if i == starter:
            result.append(a)
        else:
            result.append(b)

    return ''.join(result)


def pattern_matching(pattern: str, value: str):
    """
    You are given two strings, pattern and value. The pattern string consists of just the letters a and b,
    describing a pattern within a string. For example, the string catcatgocatgo
    matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab, and b.
    Write a method to determine if value matches pattern.
    """
    starter_char = pattern[0]
    other_char = 'b' if starter_char == 'a' else 'a'

    starter_char_count = pattern.count(starter_char)
    other_char_count = len(pattern) - starter_char_count

    starter_max_length = int(len(value) / starter_char_count)

    # Start from the max possible length of 'a'
    for i in range(starter_max_length, 0, -1):
        first = value[0: i]
        # If the count of first matches the number of counts of 'a'
        if value.count(first) == starter_char_count:
            # remove all the `first` i.e. 'cat' so that we have only non-`first`
            candidates = set(filter(lambda x: len(x) > 0, value.split(first)))
            # To match the second, the set should have only one value i.e. ('go')
            if len(candidates) != 1:
                continue
            second = list(candidates)[0]
            res = __build_from_pattern(pattern, first, second)
            if res == value:
                print(f'a={first}, b={second}', 'Pattern matches')
                return True
    print('Pattern doesn\'t matches')
    return False


if __name__ == '__main__':
    assert pattern_matching('aabab', 'catcatgocatgo')
    assert pattern_matching('abaaaaa', 'catgoocatcatcatcatcat')
    assert pattern_matching('abaaaab', 'catgoocatcatcatcatcat') == False
    assert pattern_matching('abaabaa', 'catgoocatcatgoocatcat')
    assert pattern_matching('ababaaa', 'catgoocatgoocatcatcat')
    assert pattern_matching('aba', 'zxcvbnm') == False
