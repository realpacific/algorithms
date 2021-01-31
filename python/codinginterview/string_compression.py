def string_compression(string):
    """String Compression: Implement a method to perform basic string compression using the counts of repeated
    characters. For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not
    become smaller than the original string, your method should return the original string.You can assume the string
    has only uppercase and lowercase letters (a - z). """
    if string is None or len(string) <= 1:
        return string
    compressed_string = ''
    current = None
    count = 0
    for i in string:
        if i == current or current is None:
            count += 1
        else:
            compressed_string = compressed_string + current + (str(count) if count > 1 else '')
            count = 1
        current = i
    compressed_string = compressed_string + current + str(count)
    print(compressed_string, len(compressed_string), string, len(string))

    if len(compressed_string) >= len(string):
        return string
    return compressed_string


if __name__ == "__main__":
    assert string_compression('aaaabbbbcccccaaa') == 'a4b4c5a3'
    assert string_compression('abcdef') == 'abcdef'
    assert string_compression('abababaeeee') == 'abababae4'
    assert string_compression('aaaaaa') == 'a6'
    assert string_compression('baba') == 'baba'
    assert string_compression('a') == 'a'
    assert string_compression('') == ''
    assert string_compression('aaaaaaaaaaaaaaaaabaaaaaaaaaa') == 'a17ba10'
