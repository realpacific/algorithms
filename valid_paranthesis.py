def is_valid(s):
    if len(s) % 2 != 0:
        print("Caught by length check")
        return False
    opening = ("(", "{", "[")
    closing = (")", "}", "]")
    stack = []
    for i in s:
        if i in opening:
            stack.append(i)
        elif i in closing:
            try:
                popped = stack.pop()
            except Exception:
                return False
            print(popped, i)
            if opening.index(popped) != closing.index(i):
                return False
    if len(stack) == 0:
        return True
    else:
        return False


print(is_valid("(({}))"))
print(is_valid("([][][])"))
print(is_valid("({}{}[})"))
print(is_valid("{"))
print(is_valid("}}"))
