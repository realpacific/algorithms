def string_rotation(original, test):
    """
    Check if one string is formed by rotating the other string eg: abcd, dabc, cdab, bcda
    """
    if len(original) != len(test):
        return False
    return _is_substring(test + test, original)


def _is_substring(s1, s2):
    return s2 in s1


if __name__ == "__main__":
    assert string_rotation('waterbottle', 'bottlewater')
    assert string_rotation('waterbottle', 'aterbottlew')
    assert string_rotation('abcd', 'dabc')
    assert string_rotation('abcd', 'dabc')
    assert string_rotation('abcd', 'cdab')
    assert string_rotation('abcd', 'bcda')
    assert string_rotation('abcd', 'efgh') is False
    assert string_rotation('xxxx', 'yyyy') is False
