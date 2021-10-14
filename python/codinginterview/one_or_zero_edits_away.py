def one_or_zero_edits_away(original, given):
    """
        199. One Away: There are three types of edits that can be performed on strings: insert a character,
        remove a character, or replace a character. Given two strings, write a function to check if they are one edit(or
        zero edits) away.
    """
    records_from_original = {}
    leftover_from_given = []
    for i in original:
        records_from_original.setdefault(i, 0)
        records_from_original[i] = records_from_original[i] + 1
    for i in given:
        if records_from_original.get(i) is None:
            leftover_from_given.append(i)
        else:
            records_from_original[i] = records_from_original[i] - 1

    print(records_from_original, leftover_from_given)
    return 0 <= sum(list(records_from_original.values())) <= 1 \
           and len(leftover_from_given) <= 1 \
           and min(list(records_from_original.values())) >= 0


if __name__ == "__main__":
    assert one_or_zero_edits_away('pale', 'ple')
    assert one_or_zero_edits_away('pales', 'pale')
    assert one_or_zero_edits_away('pale', 'bale')
    assert one_or_zero_edits_away('pbbb', 'bbpb')
    assert one_or_zero_edits_away('pale', 'bae') is False
    assert one_or_zero_edits_away('pbbb', 'bpbp') is False
    assert one_or_zero_edits_away('apple', 'aple')
