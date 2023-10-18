while True:
    x: str = input()
    if x == '0':
        break

    i, j = 0, len(x) - 1
    is_palindrome = True
    while i < j:
        if x[i] != x[j]:
            is_palindrome = False
            break
        else:
            i += 1
            j -= 1

    print("yes" if is_palindrome else "no")
