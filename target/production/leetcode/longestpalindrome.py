def longesPalindrome(s):
    longest_one = ""
    window = 1

    if isPalindrome(s):
        return s

    while window < len(s):
        for i in range(len(s)):
            current = s[i: i + window]
            if isPalindrome(current):
                if len(current) > len(longest_one):
                    longest_one = current
        window = window + 1

    print(longest_one)
    return longest_one


def isPalindrome(s):
    return s == s[::-1]


# assert (longesPalindrome("lababa") == "aba")
longesPalindrome("lababa")
print("---------------")
longesPalindrome("labaaba")
longesPalindrome("zznitinzx")
longesPalindrome("xxxabazzlevexxxxx")
longesPalindrome("a")
longesPalindrome("ac")
