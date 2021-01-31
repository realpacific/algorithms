def sumEvenAfterQueries(A, queries):
    s = sum(i for i in A if i % 2 == 0)
    result = []
    for i in range(len(queries)):
        val = queries[i][0]
        index = queries[i][1]

        if A[index] % 2 == 0:
            s -= A[index]
        A[index] += val

        if A[index] % 2 == 0:
            s += A[index]

        result.append(s)
    print("finally", result)


def sumEvenAfterQueries_new(A, queries):
    s = sum(i for i in A if i % 2 == 0)
    r = []
    for val, i in queries:
        A[i] += val
        if A[i] % 2 == 0:
            s += val if val % 2 == 0 else A[i]
        else:
            s -= val % 2 and A[i] - val
        r += s,
    print(r)
    return r


def sumOfEven(A):
    sum = 0
    for i in A:
        if i % 2 == 0:
            print("Even", i)
            sum += i
    return sum


if __name__ == "__main__":
    sumEvenAfterQueries(A=[1, 2, 3, 4], queries=[[1, 0], [-3, 1], [-4, 0], [2, 3]])
    print([8, 6, 2, 4])
