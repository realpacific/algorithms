def lengthOfLongestSubstring(s):
    """
    :type s: str
    :rtype: int
    """
    substring = ""
    previous = ""
    length = 0
    for char in s:
        if char not in substring:
            print(char, 'not in ', substring)
            substring += char
            length = len(substring) if length < len(substring) else length
        else:
            previous = substring
            length = len(substring) if length < len(substring) else length
            substring = ""
            try:
                substring = previous[previous.rindex(char) + 1:]
                substring += char
                print(substring, "try")
            except:
                substring += char
                print(substring, "catch")

        print(length, previous, substring)

    return length


assert (lengthOfLongestSubstring("pwwkew") == 3)
assert (lengthOfLongestSubstring("abcabcbb") == 3)
assert (lengthOfLongestSubstring("bbbbb") == 1)
assert (lengthOfLongestSubstring("aab") == 2)
assert (lengthOfLongestSubstring("dvdf") == 3)
assert (lengthOfLongestSubstring("anviaj") == 5)
