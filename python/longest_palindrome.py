def longest_palindrome(s):
    longest_one = ""
    window = 1

    if is_palindrome(s):
        return s

    while window < len(s):
        for i in range(len(s)):
            current = s[i: i + window]
            if is_palindrome(current):
                if len(current) > len(longest_one):
                    longest_one = current
        window = window + 1

    print(longest_one)
    return longest_one


def is_palindrome(s):
    return s == s[::-1]


if __name__ == "__main__":
    # assert (longest_palindrome("lababa") == "aba")
    longest_palindrome("lababa")
    print("---------------")
    longest_palindrome("labaaba")
    longest_palindrome("zznitinzx")
    longest_palindrome("xxxabazzlevexxxxx")
    longest_palindrome("a")
    longest_palindrome("ac")
