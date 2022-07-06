n = int(input())
for i in range(n):
    inputStr = input()
    inputList = inputStr.split()
    mul = int(inputList[0])
    outStr = ""
    for c in inputList[1]:
        for _ in range(mul):
            outStr = outStr + c
    print(outStr)