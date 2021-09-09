def is_palindrome_permutation(value):
    """
    195. Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
    """
    length = len(value)
    records = []
    for i in range(ord('a'), ord('z') + 1):
        records.append(0)

    # Flip and unflip the values from 0-->1 & 1-->0
    for i in value:
        pos = ord(i) - 97
        if records[pos] == 1:
            records[pos] = 0
        elif records[pos] == 0:
            records[pos] = 1

    if len(value) % 2 == 0:
        return sum(records) == 0
    else:
        return sum(records) == 1


if __name__ == "__main__":
    assert is_palindrome_permutation('level') is True
    assert is_palindrome_permutation('tacocat') is True
    assert is_palindrome_permutation('poop') is True
    assert is_palindrome_permutation('poop') is True
    assert is_palindrome_permutation('nitin') is True
    assert is_palindrome_permutation('level') is True
    assert is_palindrome_permutation('level') is True
    assert is_palindrome_permutation('level') is True
    assert is_palindrome_permutation('madam') is True
    assert is_palindrome_permutation('mom') is True
    assert is_palindrome_permutation('racecar') is True
    assert is_palindrome_permutation('redder') is True
    assert is_palindrome_permutation('repaper') is True
    assert is_palindrome_permutation('rotator') is True

    assert is_palindrome_permutation('prashant') is False
    assert is_palindrome_permutation('loop') is False
    assert is_palindrome_permutation('') is True
    assert is_palindrome_permutation('s') is True
    assert is_palindrome_permutation('xxx') is True
