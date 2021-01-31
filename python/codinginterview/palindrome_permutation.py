# 195. Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.


def is_palindrome_permutation(value):
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
    assert is_palindrome_permutation('level') == True
    assert is_palindrome_permutation('tacocat') == True
    assert is_palindrome_permutation('poop') == True
    assert is_palindrome_permutation('poop') == True
    assert is_palindrome_permutation('nitin') == True
    assert is_palindrome_permutation('level') == True
    assert is_palindrome_permutation('level') == True
    assert is_palindrome_permutation('level') == True
    assert is_palindrome_permutation('madam') == True
    assert is_palindrome_permutation('mom') == True
    assert is_palindrome_permutation('racecar') == True
    assert is_palindrome_permutation('redder') == True
    assert is_palindrome_permutation('repaper') == True
    assert is_palindrome_permutation('rotator') == True

    assert is_palindrome_permutation('prashant') == False
    assert is_palindrome_permutation('loop') == False
    assert is_palindrome_permutation('') == True
    assert is_palindrome_permutation('s') == True
    assert is_palindrome_permutation('xxx') == True
