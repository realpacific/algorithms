def code(char: str):
    if char == 'B':
        return 0
    if char == 'Y':
        return 1
    if char == 'R':
        return 2
    if char == 'G':
        return 3
    else:
        return -0


def calculate(guess: str, solution: str):
    hits = 0
    psuedo_hits = 0

    indices = []
    for i in range(0, len(solution)):
        if guess[i] == solution[i]:
            indices.append(i)
            hits += 1

    for i in range(0, len(solution)):
        if guess[i] in solution and i not in indices:
            psuedo_hits += 1
            indices.append(i)

    return hits, psuedo_hits


if __name__ == '__main__':
    assert calculate(guess='RGYB', solution='BGRR') == (1, 2)
    assert calculate(guess='GRBG', solution='GRBG') == (4, 0)
    assert calculate(guess='RRRR', solution='BBBB') == (0, 0)
    assert calculate(guess='RGBY', solution='RGBY') == (4, 0)
    assert calculate(guess='BGRY', solution='GBYR') == (0, 4)
    assert calculate(guess='BRGY', solution='BRYY') == (3, 0)
