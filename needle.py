# def strStr(haystack: str, needle: str) -> int:
#     ned_len = len(needle)
#     if ned_len == 0 or needle == haystack:
#         return 0
#     if ned_len > len(haystack):
#         return -1
#     for i in range(0, len(haystack)):
#         if haystack[i: i + ned_len] == needle:
#             return i
#     return -1


def strStr(haystack: str, needle: str) -> int:
    ned_len = len(needle)
    if ned_len == 0 or needle == haystack:
        return 0
    if ned_len > len(haystack):
        return -1
    i = 0
    while i < len(haystack):
        window = haystack[i: i + ned_len]
        if window == needle:
            return i
        elif window.find(needle[0]) > 0:
            i = i + window.find(needle[0])
        else:
            i += 1

    # for i in range(0, len(haystack)):
    #     if haystack[i: i + ned_len] == needle:
    #         return i
    return -1


print(strStr("hellove", 'lov'))
print(strStr("hellove", 'e'))
print(strStr("hellove", 'ov'))
print(strStr("hello", 'zzzzzzzll'))
print(strStr("hello", 'hello'))
print(strStr(haystack="aaaaa", needle="bba"))
