def string_rotation(original, test):
    if len(original) != len(test):
        return False
    return _is_substring(test + test, original)


def _is_substring(s1, s2):
    return s2 in s1


if __name__ == "__main__":
    assert string_rotation('waterbottle', 'bottlewater')
    assert string_rotation('waterbottle', 'aterbottlew')
    assert string_rotation('abcd', 'dabc')
    assert string_rotation('abcd', 'efgh') == False
    assert string_rotation('xxxx', 'yyyy') == False
